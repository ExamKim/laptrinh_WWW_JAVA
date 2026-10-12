package fit.vn.midterm1.repository.impl;

import fit.vn.midterm1.entity.Product;
import fit.vn.midterm1.repository.ProductRepository;
import fit.vn.midterm1.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getString("image_url")
                    );
                    list.add(p);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
    }

        @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM product WHERE id=? ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getString("image_url")
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
        String sql = "DELETE FROM product WHERE id=? ";
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
    public boolean update(Product p) {
        String sql = "UPDATE product SET name=?, price=?, description=?,image_url=? WHERE id=? ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(5,p.getId());
                ps.setString(1,p.getName());
                ps.setDouble(2,p.getPrice());
                ps.setString(3,p.getDescription());
                ps.setString(4,p.getImage_url());
                return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            }
    }

    @Override
    public boolean save(Product p) {
        String sql = "INSERT INTO product(name,price,description,image_url) VALUE (?,?,?,?) ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,p.getName());
            ps.setDouble(2,p.getPrice());
            ps.setString(3,p.getDescription());
            ps.setString(4,p.getImage_url());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
