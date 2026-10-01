package vn.nhutthanh.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Product")
public class Product_24162118 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private int productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productCode")
    private long productCode;

    @Column(name = "categoryId")
    private int categoryId;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private int amount;

    @Column(name = "stock")
    private int stock;

    @Column(name = "images")
    private String images;

    @Column(name = "wishlist")
    private int wishlist;

    @Column(name = "status")
    private int status;

    @Temporal(TemporalType.DATE)
    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "sellerId")
    private int sellerId;

    public Product_24162118() {}

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public long getProductCode() { return productCode; }
    public void setProductCode(long productCode) { this.productCode = productCode; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
    public int getWishlist() { return wishlist; }
    public void setWishlist(int wishlist) { this.wishlist = wishlist; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }
}