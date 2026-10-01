package vn.nhutthanh.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CartItem")
public class CartItem_24162118 implements Serializable {

    @Id
    @Column(name = "cartItemId")
    private String cartItemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unitPrice")
    private double unitPrice;

    @Column(name = "productId")
    private int productId;

    @Column(name = "cartId")
    private String cartId;

    public CartItem_24162118() {}

    public String getCartItemId() { return cartItemId; }
    public void setCartItemId(String cartItemId) { this.cartItemId = cartItemId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getCartId() { return cartId; }
    public void setCartId(String cartId) { this.cartId = cartId; }
}