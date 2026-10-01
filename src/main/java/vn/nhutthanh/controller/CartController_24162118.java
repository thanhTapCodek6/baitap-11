package vn.nhutthanh.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.nhutthanh.entity.CartLine_24162118;
import vn.nhutthanh.entity.Users_24162118;
import vn.nhutthanh.service.CartService_24162118;

@WebServlet(urlPatterns = { "/cart", "/cart/add", "/cart/update", "/cart/remove" })
public class CartController_24162118 extends HttpServlet {

    private CartService_24162118 service = new CartService_24162118();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users_24162118 user = (Users_24162118) session.getAttribute("user");

        List<CartLine_24162118> lines = service.getOpenLines(user.getUserId());
        req.setAttribute("lines", lines);
        req.setAttribute("total", service.sum(lines));

        // Thông báo lỗi chỉ hiện 1 lần
        Object msg = session.getAttribute("msg");
        if (msg != null) {
            req.setAttribute("msg", msg);
            session.removeAttribute("msg");
        }
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Users_24162118 user = (Users_24162118) session.getAttribute("user");
        String uri = req.getRequestURI();

        String error;
        try {
            if (uri.endsWith("/add")) {
                int productId = Integer.parseInt(req.getParameter("productId"));
                int qty = parseQty(req.getParameter("quantity"));
                error = service.addItem(user.getUserId(), productId, qty);
            } else if (uri.endsWith("/update")) {
                error = service.updateItem(user.getUserId(), req.getParameter("cartItemId"),
                        parseQty(req.getParameter("quantity")));
            } else if (uri.endsWith("/remove")) {
                error = service.removeItem(user.getUserId(), req.getParameter("cartItemId"));
            } else {
                error = null;
            }
        } catch (NumberFormatException e) {
            error = "Dữ liệu không hợp lệ";
        }

        if (error != null) {
            session.setAttribute("msg", error);
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    private int parseQty(String s) {
        return (s == null || s.isBlank()) ? 1 : Integer.parseInt(s.trim());
    }
}
