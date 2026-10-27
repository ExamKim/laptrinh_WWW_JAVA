package com.example.eshopapi.service;

import com.example.eshopapi.dto.CategoryRequest;
import com.example.eshopapi.dto.CategoryResponse;
import com.example.eshopapi.dto.CategoryStatsResponse;
import com.example.eshopapi.dto.ProductResponse;
import com.example.eshopapi.entity.Category;
import com.example.eshopapi.entity.Product;
import com.example.eshopapi.exception.ConflictException;
import com.example.eshopapi.exception.NotFoundException;
import com.example.eshopapi.repository.CategoryRepository;
import com.example.eshopapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAllByOrderByNameAsc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục id = " + id));
        return toResponse(category);
    }

    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.name())) {
            throw new ConflictException("Tên danh mục đã tồn tại");
        }

        Category category = Category.builder()
                .name(request.name())
                .description(request.description())
                .build();

        Category saved = categoryRepository.save(category);
        return toResponse(saved);
    }

    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục id = " + id));

        if (categoryRepository.existsByNameIgnoreCaseAndIdNot(request.name(), id)) {
            throw new ConflictException("Tên danh mục đã tồn tại");
        }

        category.setName(request.name());
        category.setDescription(request.description());

        Category updated = categoryRepository.save(category);
        return toResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục id = " + id));

        if (productRepository.existsByCategoryId(id)) {
            throw new ConflictException("Không thể xóa danh mục đang có sản phẩm");
        }

        categoryRepository.delete(category);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findProducts(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy danh mục id = " + id);
        }

        List<Product> products = productRepository.findByCategoryId(id);
        return products.stream()
                .map(this::toProductResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CategoryStatsResponse> getCategoryStats() {
        return categoryRepository.getCategoryStats();
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    private ProductResponse toProductResponse(Product product) {
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
