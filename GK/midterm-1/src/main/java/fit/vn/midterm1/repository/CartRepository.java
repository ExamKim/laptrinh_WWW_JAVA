package fit.vn.midterm1.repository;

import fit.vn.midterm1.dto.CartDetailDTO;
import fit.vn.midterm1.entity.Cart;
import fit.vn.midterm1.entity.Product;

import java.util.List;

public interface CartRepository {
        List<Cart> findAll();
        Cart findById(int id);
        boolean delete(int id);
        boolean update(Cart c);
        boolean save(Cart c);
        List<CartDetailDTO> cartDetail();
}
