
package com.example.eshopapi.service;

import com.example.eshopapi.entity.Category;
import com.example.eshopapi.entity.Product;
import com.example.eshopapi.repository.CategoryRepository;
import com.example.eshopapi.repository.ProductRepository;
import com.example.eshopapi.exception.ConflictException;
import com.example.eshopapi.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // P1: Tìm kiếm và phân trang
    @Transactional(readOnly = true)
    public Page<Product> search(
            String keyword,
            Long categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable) {

        return productRepository.search(
                keyword,
                categoryId,
                minPrice,
                maxPrice,
                pageable);
    }

    // P2: Lấy sản phẩm theo ID
    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy sản phẩm id = " + id));
    }

    // P3: Thêm sản phẩm
    @Transactional
    public Product create(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy danh mục id = "
                                        + categoryId));

        product.setCategory_id(category);

        return productRepository.save(product);
    }

    // P4: Cập nhật sản phẩm
    @Transactional
    public Product update(
            Long id,
            Product request,
            Long categoryId) {

        Product product = getById(id);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy danh mục id = "
                                        + categoryId));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImage_url(request.getImage_url());
        product.setCategory_id(category);

        // Không sửa createdAt
        return productRepository.save(product);
    }

    // P5: Xóa sản phẩm
    @Transactional
    public void delete(Long id) {
        Product product = getById(id);

        // Bắt lỗi nếu sản phẩm đã được sử dụng trong order_detail.
        // Cần có bảng và khóa ngoại tương ứng trong database.
        try {
            productRepository.delete(product);
            productRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ConflictException(
                    "Không thể xóa sản phẩm đang được sử dụng trong đơn hàng");
        }
    }
}
