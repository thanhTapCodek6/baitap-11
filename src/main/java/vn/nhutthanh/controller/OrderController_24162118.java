package vn.nhutthanh.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.nhutthanh.entity.Users_24162118;
import vn.nhutthanh.service.CartService_24162118;
import vn.nhutthanh.util.OrderStatus_24162118;

// Lịch sử đặt hàng, lọc theo trạng thái (?status=1..8)
@WebServlet(urlPatterns = { "/orders" })
public class OrderController_24162118 extends HttpServlet {

    private CartService_24162118 service = new CartService_24162118();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Users_24162118 user = (Users_24162118) req.getSession().getAttribute("user");

        Integer status = null;
        String s = req.getParameter("status");
        if (s != null && !s.isBlank()) {
            try {
                int v = Integer.parseInt(s.trim());
                if (OrderStatus_24162118.LABELS.containsKey(v)) {
                    status = v;
                }
            } catch (NumberFormatException e) {
                // bỏ qua, hiện tất cả
            }
        }

        req.setAttribute("orders", service.getOrders(user.getUserId(), status));
        req.setAttribute("statusLabels", OrderStatus_24162118.LABELS);
        req.setAttribute("current", status);
        req.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(req, resp);
    }
}
