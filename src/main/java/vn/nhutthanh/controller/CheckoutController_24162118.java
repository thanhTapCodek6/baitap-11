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

// Thanh toán khi nhận hàng (COD)
@WebServlet(urlPatterns = { "/checkout" })
public class CheckoutController_24162118 extends HttpServlet {

    private CartService_24162118 service = new CartService_24162118();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Users_24162118 user = (Users_24162118) req.getSession().getAttribute("user");
        List<CartLine_24162118> lines = service.getOpenLines(user.getUserId());
        if (lines.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        req.setAttribute("lines", lines);
        req.setAttribute("total", service.sum(lines));
        req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users_24162118 user = (Users_24162118) session.getAttribute("user");

        String error = service.checkout(user.getUserId());
        if (error != null) {
            session.setAttribute("msg", error);
            resp.sendRedirect(req.getContextPath() + "/cart");
        } else {
            resp.sendRedirect(req.getContextPath() + "/orders?placed=1");
        }
    }
}
