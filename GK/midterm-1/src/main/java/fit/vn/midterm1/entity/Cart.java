package fit.vn.midterm1.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Cart {
    private int id;
    private int product_id;
    private String customer_name;
    private int quantity;
    private Timestamp created_at;

    public Cart() {
    }

    public Cart(int id, int product_id, String customer_name, int quantity, Timestamp created_at) {
        this.id = id;
        this.product_id = product_id;
        this.customer_name = customer_name;
        this.quantity = quantity;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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
}
