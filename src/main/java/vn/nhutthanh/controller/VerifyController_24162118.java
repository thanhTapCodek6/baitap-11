package vn.nhutthanh.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.nhutthanh.service.UserService_24162118;

@WebServlet(urlPatterns = { "/verify" })
public class VerifyController_24162118 extends HttpServlet {

    private UserService_24162118 userService = new UserService_24162118();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String otp = req.getParameter("otp");

        if (userService.activate(email, otp)) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("email", email);
            req.setAttribute("error", "Mã OTP không đúng");
            req.getRequestDispatcher("/WEB-INF/views/verify.jsp").forward(req, resp);
        }
    }
}