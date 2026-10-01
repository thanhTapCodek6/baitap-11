package vn.nhutthanh.controller;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.nhutthanh.entity.Users_24162118;
import vn.nhutthanh.service.UserService_24162118;
import vn.nhutthanh.util.MailUtil_24162118;

@WebServlet(urlPatterns = { "/register" })
public class RegisterController_24162118 extends HttpServlet {

    private UserService_24162118 userService = new UserService_24162118();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");

        if (userService.existsUsername(username)) {
            req.setAttribute("error", "Tên đăng nhập đã tồn tại");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        if (userService.existsEmail(email)) {
            req.setAttribute("error", "Email đã được sử dụng");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        // Sinh OTP 6 số
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        Users_24162118 user = new Users_24162118();
        user.setUsername(username);
        user.setEmail(email);
        user.setFullname(fullname);
        user.setPassword(password);
        user.setPhone(phone);
        user.setImages("");
        user.setStatus(0);      // 0 = chưa kích hoạt
        user.setCode(otp);
        user.setRoleId(2);      // 2 = User
        user.setSellerid(null);

        try {
            MailUtil_24162118.send(email, "Mã OTP kích hoạt tài khoản",
                    "Mã OTP của bạn là: " + otp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Không gửi được mail OTP, kiểm tra lại email");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        userService.register(user);
        req.setAttribute("email", email);
        req.getRequestDispatcher("/WEB-INF/views/verify.jsp").forward(req, resp);
    }
}