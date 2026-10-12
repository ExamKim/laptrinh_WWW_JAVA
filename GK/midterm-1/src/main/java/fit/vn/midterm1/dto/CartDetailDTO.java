package fit.vn.midterm1.dto;

import java.sql.Timestamp;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class CartDetailDTO {
    private int cartId;
    private String customer_name;
    private int quantity;
    private Timestamp created_at;
    private String name;
    private double price;

    public CartDetailDTO() {
    }

    public CartDetailDTO(int cartId, String customer_name, int quantity, Timestamp created_at, String name, double price) {
        this.cartId = cartId;
        this.customer_name = customer_name;
        this.quantity = quantity;
        this.created_at = created_at;
        this.name = name;
        this.price = price;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
