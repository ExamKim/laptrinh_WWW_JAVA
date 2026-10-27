
package com.example.eshopapi.service;

import com.example.eshopapi.entity.Category;
import com.example.eshopapi.entity.Product;
import com.example.eshopapi.repository.CategoryRepository;
import com.example.eshopapi.repository.ProductRepository;
import com.example.eshopapi.exception.ConflictException;
import com.example.eshopapi.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    // C1: Lấy tất cả danh mục, sắp xếp theo tên
    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return categoryRepository.findAllByOrderByNameAsc();
    }

    // C2: Lấy danh mục theo ID
    @Transactional(readOnly = true)
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy danh mục id = " + id));
    }

    // C3: Thêm danh mục
    @Transactional
    public Category create(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(
                category.getName())) {
            throw new ConflictException(
                    "Tên danh mục đã tồn tại");
        }

        return categoryRepository.save(category);
    }

    // C4: Cập nhật danh mục
    @Transactional
    public Category update(Long id, Category request) {
        Category category = getById(id);

        if (categoryRepository.existsByNameIgnoreCaseAndIdNot(
                request.getName(), id)) {
            throw new ConflictException(
                    "Tên danh mục đã tồn tại");
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return categoryRepository.save(category);
    }

    // C5: Xóa danh mục
    @Transactional
    public void delete(Long id) {
        Category category = getById(id);

        if (productRepository.existsByCategoryId(id)) {
            throw new ConflictException(
                    "Không thể xóa danh mục đang có sản phẩm");
        }

        categoryRepository.delete(category);
    }

    // C6: Lấy các sản phẩm thuộc danh mục
    @Transactional(readOnly = true)
    public List<Product> getProducts(Long id) {
        getById(id);
        return productRepository.findByCategoryId(id);
    }
}
