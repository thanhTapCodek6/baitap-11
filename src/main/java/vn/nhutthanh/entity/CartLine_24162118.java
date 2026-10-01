package vn.nhutthanh.entity;

public class CartLine_24162118 {

    private CartItem_24162118 item;
    private Product_24162118 product;
    private double unitPrice;

    public CartLine_24162118(CartItem_24162118 item, Product_24162118 product, double unitPrice) {
        this.item = item;
        this.product = product;
        this.unitPrice = unitPrice;
    }

    public CartItem_24162118 getItem() { return item; }
    public Product_24162118 getProduct() { return product; }
    public double getUnitPrice() { return unitPrice; }
    public double getTotal() { return unitPrice * item.getQuantity(); }
}
