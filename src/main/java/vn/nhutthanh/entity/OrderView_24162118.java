package vn.nhutthanh.entity;

import java.util.List;

public class OrderView_24162118 {

    private Cart_24162118 cart;
    private List<CartLine_24162118> lines;
    private double total;

    public OrderView_24162118(Cart_24162118 cart, List<CartLine_24162118> lines, double total) {
        this.cart = cart;
        this.lines = lines;
        this.total = total;
    }

    public Cart_24162118 getCart() { return cart; }
    public List<CartLine_24162118> getLines() { return lines; }
    public double getTotal() { return total; }
}
