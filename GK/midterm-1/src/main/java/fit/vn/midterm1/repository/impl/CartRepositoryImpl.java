package fit.vn.midterm1.repository.impl;

import fit.vn.midterm1.entity.Cart;
import fit.vn.midterm1.entity.Product;
import fit.vn.midterm1.repository.CartRepository;
import fit.vn.midterm1.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartRepositoryImpl implements CartRepository {

    @Override
    public List<Cart> findAll() {
        List<Cart> list = new ArrayList<>();
        String sql = "SELECT * FROM shoppingcart";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cart c = new Cart(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getString("customer_name"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("created_at")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Cart findById(int id) {
        String sql = "SELECT * FROM shoppingcart WHERE id=? ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cart(
                            rs.getInt("id"),
                            rs.getInt("product_id"),
                            rs.getString("customer_name"),
                            rs.getInt("quantity"),
                            rs.getTimestamp("created_at")
                    );
                }
            } catch (SQLException e) {
                throw new RuntimeException("Loi truy van sp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM shoppingcart WHERE id=? ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Cart c) {
        String sql = "UPDATE shoppingcart SET product_id=?, customer_name=?, quantity=? WHERE id=? ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(4,c.getId());
            ps.setInt(1,c.getProduct_id());
            ps.setString(2,c.getCustomer_name());
            ps.setInt(3,c.getQuantity());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean save(Cart c) {
        String sql = "INSERT INTO shoppingcart(product_id,customer_name,quantity,created_at) VALUE (?,?,?,?) ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,c.getProduct_id());
            ps.setString(2,c.getCustomer_name());
            ps.setInt(3,c.getQuantity());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
