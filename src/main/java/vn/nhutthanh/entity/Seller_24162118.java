package vn.nhutthanh.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Seller")
public class Seller_24162118 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sellerId")
    private int sellerId;

    @Column(name = "sellername")
    private String sellername;

    @Column(name = "images")
    private String images;

    @Column(name = "status")
    private int status;

    public Seller_24162118() {}

    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }
    public String getSellername() { return sellername; }
    public void setSellername(String sellername) { this.sellername = sellername; }
    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}