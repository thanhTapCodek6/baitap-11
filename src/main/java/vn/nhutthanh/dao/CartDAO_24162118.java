package vn.nhutthanh.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import vn.nhutthanh.entity.CartItem_24162118;
import vn.nhutthanh.entity.Cart_24162118;
import vn.nhutthanh.entity.Product_24162118;
import vn.nhutthanh.util.JPAConfig_24162118;
import vn.nhutthanh.util.OrderStatus_24162118;

public class CartDAO_24162118 {

    private static String newId(String prefix) {
        return prefix + UUID.randomUUID().toString().replace("-", "").substring(0, 20);
    }

    // Giỏ hàng đang mua của user (status = 0), null nếu chưa có
    private Cart_24162118 findOpen(EntityManager em, int userId) {
        List<Cart_24162118> l = em.createQuery(
                "SELECT c FROM Cart_24162118 c WHERE c.userId = :u AND c.status = :s",
                Cart_24162118.class)
                .setParameter("u", userId)
                .setParameter("s", OrderStatus_24162118.CART)
                .getResultList();
        return l.isEmpty() ? null : l.get(0);
    }

    public Cart_24162118 findOpenCart(int userId) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            return findOpen(em, userId);
        } finally {
            em.close();
        }
    }

    public List<CartItem_24162118> findItems(String cartId) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT i FROM CartItem_24162118 i WHERE i.cartId = :c ORDER BY i.cartItemId",
                    CartItem_24162118.class)
                    .setParameter("c", cartId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Thêm sản phẩm vào giỏ; trả về thông báo lỗi hoặc null nếu thành công
    public String addItem(int userId, int productId, int qty) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            Product_24162118 p = em.find(Product_24162118.class, productId);
            if (p == null) {
                t.rollback();
                return "Sản phẩm không tồn tại";
            }

            Cart_24162118 c = findOpen(em, userId);
            if (c == null) {
                c = new Cart_24162118();
                c.setCartId(newId("C"));
                c.setUserId(userId);
                c.setBuyDate(new Date());
                c.setStatus(OrderStatus_24162118.CART);
                em.persist(c);
            }

            List<CartItem_24162118> l = em.createQuery(
                    "SELECT i FROM CartItem_24162118 i WHERE i.cartId = :c AND i.productId = :p",
                    CartItem_24162118.class)
                    .setParameter("c", c.getCartId())
                    .setParameter("p", productId)
                    .getResultList();
            CartItem_24162118 it = l.isEmpty() ? null : l.get(0);

            int newQty = qty + (it != null ? it.getQuantity() : 0);
            if (newQty > p.getStock()) {
                t.rollback();
                return "Chỉ còn " + p.getStock() + " sản phẩm \"" + p.getProductName() + "\" trong kho";
            }

            if (it == null) {
                it = new CartItem_24162118();
                it.setCartItemId(newId("I"));
                it.setCartId(c.getCartId());
                it.setProductId(productId);
                em.persist(it);
            }
            it.setQuantity(newQty);
            it.setUnitPrice(p.getPrice());

            t.commit();
            return null;
        } catch (RuntimeException e) {
            if (t.isActive()) t.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Đổi số lượng (qty <= 0 thì xóa khỏi giỏ); trả về lỗi hoặc null
    public String updateItem(int userId, String cartItemId, int qty) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            Cart_24162118 c = findOpen(em, userId);
            CartItem_24162118 it = em.find(CartItem_24162118.class, cartItemId);
            if (c == null || it == null || !c.getCartId().equals(it.getCartId())) {
                t.rollback();
                return "Không tìm thấy sản phẩm trong giỏ hàng";
            }

            if (qty <= 0) {
                em.remove(it);
                t.commit();
                return null;
            }

            Product_24162118 p = em.find(Product_24162118.class, it.getProductId());
            if (p == null) {
                t.rollback();
                return "Sản phẩm không còn tồn tại";
            }
            if (qty > p.getStock()) {
                t.rollback();
                return "Chỉ còn " + p.getStock() + " sản phẩm \"" + p.getProductName() + "\" trong kho";
            }

            it.setQuantity(qty);
            it.setUnitPrice(p.getPrice());
            t.commit();
            return null;
        } catch (RuntimeException e) {
            if (t.isActive()) t.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Đặt hàng COD: kiểm tra + trừ kho, chuyển giỏ thành đơn mới (status = 1)
    public String checkout(int userId) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            Cart_24162118 c = findOpen(em, userId);
            List<CartItem_24162118> items = (c == null) ? List.of()
                    : em.createQuery("SELECT i FROM CartItem_24162118 i WHERE i.cartId = :c",
                            CartItem_24162118.class)
                            .setParameter("c", c.getCartId())
                            .getResultList();
            if (items.isEmpty()) {
                t.rollback();
                return "Giỏ hàng đang trống";
            }

            for (CartItem_24162118 it : items) {
                Product_24162118 p = em.find(Product_24162118.class, it.getProductId(),
                        LockModeType.PESSIMISTIC_WRITE);
                if (p == null) {
                    t.rollback();
                    return "Có sản phẩm không còn tồn tại, vui lòng xóa khỏi giỏ";
                }
                if (it.getQuantity() > p.getStock()) {
                    t.rollback();
                    return "Sản phẩm \"" + p.getProductName() + "\" chỉ còn " + p.getStock() + " trong kho";
                }
                p.setStock(p.getStock() - it.getQuantity());
                it.setUnitPrice(p.getPrice());
            }

            c.setStatus(OrderStatus_24162118.NEW);
            c.setBuyDate(new Date());
            t.commit();
            return null;
        } catch (RuntimeException e) {
            if (t.isActive()) t.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Lịch sử đơn hàng (status >= 1), lọc theo status nếu có; mới nhất trước
    public List<Cart_24162118> findOrders(int userId, Integer status) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            String jpql = "SELECT c FROM Cart_24162118 c WHERE c.userId = :u AND c.status >= 1"
                    + (status != null ? " AND c.status = :s" : "")
                    + " ORDER BY c.buyDate DESC";
            var q = em.createQuery(jpql, Cart_24162118.class).setParameter("u", userId);
            if (status != null) {
                q.setParameter("s", status);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
