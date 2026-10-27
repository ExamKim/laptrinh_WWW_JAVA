package com.example.eshopapi.service;

import com.example.eshopapi.dto.PageResponse;
import com.example.eshopapi.dto.ProductRequest;
import com.example.eshopapi.dto.ProductResponse;
import com.example.eshopapi.dto.StockUpdateRequest;
import com.example.eshopapi.entity.Category;
import com.example.eshopapi.entity.Product;
import com.example.eshopapi.exception.ConflictException;
import com.example.eshopapi.exception.NotFoundException;
import com.example.eshopapi.repository.CategoryRepository;
import com.example.eshopapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Set<String> ALLOWED_SORT_PROPERTIES = Set.of(
            "id", "name", "price", "quantity", "createdAt"
    );

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> search(
            String keyword,
            Long categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            int page,
            int size,
            String sort) {

        Sort sortObj = parseSort(sort);
        Pageable pageable = PageRequest.of(page, size, sortObj);

        String searchKeyword = (keyword != null && !keyword.isBlank()) ? keyword.trim() : null;

        Page<Product> pageResult = productRepository.search(
                searchKeyword,
                categoryId,
                minPrice,
                maxPrice,
                pageable
        );

        List<ProductResponse> content = pageResult.getContent()
                .stream()
                .map(this::toResponse)
                .toList();

        return new PageResponse<>(
                content,
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm id = " + id));
        return toResponse(product);
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục id = " + request.categoryId()));

        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .quantity(request.quantity())
                .imageUrl(request.imageUrl())
                .category(category)
                .build();

        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm id = " + id));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục id = " + request.categoryId()));

        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        product.setImageUrl(request.imageUrl());
        product.setCategory(category);

        Product updated = productRepository.save(product);
        return toResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm id = " + id));

        try {
            productRepository.delete(product);
            productRepository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException("Không thể xóa sản phẩm đã có trong đơn hàng");
        }
    }

    @Transactional
    public ProductResponse updateStock(Long id, StockUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm id = " + id));

        int newStock = product.getQuantity() + request.delta();
        if (newStock < 0) {
            throw new ConflictException("Tồn kho sau khi trừ không được nhỏ hơn 0");
        }

        product.setQuantity(newStock);
        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    private Sort parseSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.by(Sort.Direction.ASC, "id");
        }

        String[] parts = sort.split(",");
        String property = parts[0].trim();

        if (!ALLOWED_SORT_PROPERTIES.contains(property)) {
            throw new IllegalArgumentException("Thuộc tính sắp xếp không hợp lệ: " + property);
        }

        Sort.Direction direction = (parts.length > 1 && parts[1].trim().equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return Sort.by(direction, property);
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getImageUrl(),
                product.getCategory() != null ? product.getCategory().getId() : null,
                product.getCategory() != null ? product.getCategory().getName() : null,
                product.getCreatedAt()
        );
    }
}
