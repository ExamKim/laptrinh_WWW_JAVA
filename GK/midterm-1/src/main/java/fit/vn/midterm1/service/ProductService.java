package fit.vn.midterm1.service;

import fit.vn.midterm1.entity.Product;
import fit.vn.midterm1.repository.ProductRepository;
import fit.vn.midterm1.repository.impl.ProductRepositoryImpl;

import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/11/2026
 */
public class ProductService {
    private ProductRepository productRepository= new ProductRepositoryImpl();

    public List<Product> getAllProducts(){return productRepository.findAll();}
    public Product getProductById(int id){return productRepository.findById(id);}
    public boolean createProduct(Product p){return productRepository.save(p);}
    public boolean updateProduct(Product p){return productRepository.update(p);}
    public boolean deleteProduct(int id){return productRepository.delete(id);}

}
