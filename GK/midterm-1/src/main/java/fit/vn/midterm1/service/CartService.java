package fit.vn.midterm1.service;

import fit.vn.midterm1.entity.Cart;
import fit.vn.midterm1.repository.CartRepository;
import fit.vn.midterm1.repository.impl.CartRepositoryImpl;

import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class CartService {
    private CartRepository cartRepository= new CartRepositoryImpl();

    public List<Cart> getAllCarts(){return cartRepository.findAll();}
    public Cart getCartById(int id){return cartRepository.findById(id);}
    public boolean createCart(Cart c){return cartRepository.save(c);}
    public boolean updateCart(Cart c){return cartRepository.update(c);}
    public boolean deleteCart(int id){return cartRepository.delete(id);}
}
