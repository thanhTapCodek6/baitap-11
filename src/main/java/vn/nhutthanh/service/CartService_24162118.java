package vn.nhutthanh.service;

import java.util.ArrayList;
import java.util.List;

import vn.nhutthanh.dao.CartDAO_24162118;
import vn.nhutthanh.dao.ProductDAO_24162118;
import vn.nhutthanh.entity.CartItem_24162118;
import vn.nhutthanh.entity.CartLine_24162118;
import vn.nhutthanh.entity.Cart_24162118;
import vn.nhutthanh.entity.OrderView_24162118;
import vn.nhutthanh.entity.Product_24162118;

public class CartService_24162118 {

    private CartDAO_24162118 cartDAO = new CartDAO_24162118();
    private ProductDAO_24162118 productDAO = new ProductDAO_24162118();

    public String addItem(int userId, int productId, int qty) {
        if (qty < 1) return "Số lượng không hợp lệ";
        return cartDAO.addItem(userId, productId, qty);
    }

    public String updateItem(int userId, String cartItemId, int qty) {
        return cartDAO.updateItem(userId, cartItemId, qty);
    }

    public String removeItem(int userId, String cartItemId) {
        return cartDAO.updateItem(userId, cartItemId, 0);
    }

    public String checkout(int userId) {
        return cartDAO.checkout(userId);
    }

    // Các dòng trong giỏ hàng hiện tại (dùng giá hiện hành của sản phẩm)
    public List<CartLine_24162118> getOpenLines(int userId) {
        Cart_24162118 c = cartDAO.findOpenCart(userId);
        if (c == null) return new ArrayList<>();
        return buildLines(c.getCartId(), true);
    }

    public double sum(List<CartLine_24162118> lines) {
        double total = 0;
        for (CartLine_24162118 l : lines) total += l.getTotal();
        return total;
    }

    public List<OrderView_24162118> getOrders(int userId, Integer status) {
        List<OrderView_24162118> result = new ArrayList<>();
        for (Cart_24162118 c : cartDAO.findOrders(userId, status)) {
            List<CartLine_24162118> lines = buildLines(c.getCartId(), false);
            result.add(new OrderView_24162118(c, lines, sum(lines)));
        }
        return result;
    }

    private List<CartLine_24162118> buildLines(String cartId, boolean currentPrice) {
        List<CartLine_24162118> lines = new ArrayList<>();
        for (CartItem_24162118 it : cartDAO.findItems(cartId)) {
            Product_24162118 p = productDAO.findById(it.getProductId());
            if (p == null) continue;
            lines.add(new CartLine_24162118(it, p, currentPrice ? p.getPrice() : it.getUnitPrice()));
        }
        return lines;
    }
}
