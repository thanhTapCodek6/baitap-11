package vn.nhutthanh.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.nhutthanh.entity.Users_24162118;
import vn.nhutthanh.service.UserService_24162118;

@WebServlet(urlPatterns = { "/login" })
public class LoginController_24162118 extends HttpServlet {

    private UserService_24162118 userService = new UserService_24162118();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Users_24162118 user = userService.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            // roleId: 1 = Admin, 2 = User, 3 = Seller
            int role = user.getRoleId();
            if (role == 3) {
                resp.sendRedirect(req.getContextPath() + "/seller/home");
            } else {
                // Admin (1) và User (2) về trang chủ user
                resp.sendRedirect(req.getContextPath() + "/user/home");
            }
        } else {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu, hoặc tài khoản chưa kích hoạt");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}