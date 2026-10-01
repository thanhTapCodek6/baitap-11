package vn.nhutthanh.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Cart")
public class Cart_24162118 implements Serializable {

    @Id
    @Column(name = "cartId")
    private String cartId;

    @Column(name = "userId")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "buyDate")
    private Date buyDate;

    @Column(name = "status")
    private int status;

    public Cart_24162118() {}

    public String getCartId() { return cartId; }
    public void setCartId(String cartId) { this.cartId = cartId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Date getBuyDate() { return buyDate; }
    public void setBuyDate(Date buyDate) { this.buyDate = buyDate; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}