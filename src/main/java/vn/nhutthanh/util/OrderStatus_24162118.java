package vn.nhutthanh.util;

import java.util.LinkedHashMap;
import java.util.Map;

// Cart.status: 0 = giỏ hàng đang mua, 1..8 = trạng thái đơn hàng
public final class OrderStatus_24162118 {

    public static final int CART = 0;
    public static final int NEW = 1;
    public static final int CONFIRMED = 2;
    public static final int PREPARING = 3;
    public static final int SHIPPING = 4;
    public static final int DELIVERING = 5;
    public static final int DELIVERED = 6;
    public static final int CANCELLED = 7;
    public static final int RETURNED = 8;

    public static final Map<Integer, String> LABELS = new LinkedHashMap<>();
    static {
        LABELS.put(NEW, "Đơn hàng mới");
        LABELS.put(CONFIRMED, "Đã xác nhận");
        LABELS.put(PREPARING, "Chuẩn bị hàng");
        LABELS.put(SHIPPING, "Vận chuyển");
        LABELS.put(DELIVERING, "Giao hàng");
        LABELS.put(DELIVERED, "Đã giao");
        LABELS.put(CANCELLED, "Đơn hàng hủy");
        LABELS.put(RETURNED, "Đơn hàng hoàn");
    }

    private OrderStatus_24162118() {}
}
