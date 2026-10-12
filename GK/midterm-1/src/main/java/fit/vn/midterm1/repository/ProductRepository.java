package fit.vn.midterm1.repository;

import fit.vn.midterm1.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(int id);
    boolean delete(int id);
    boolean update(Product product);
    boolean save(Product product);
}
