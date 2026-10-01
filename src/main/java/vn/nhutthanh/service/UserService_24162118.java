package vn.nhutthanh.service;

import vn.nhutthanh.dao.UserDAO_24162118;
import vn.nhutthanh.entity.Users_24162118;

public class UserService_24162118 {

    private UserDAO_24162118 userDAO = new UserDAO_24162118();

    // Đăng nhập: trả về user nếu đúng, null nếu sai hoặc chưa kích hoạt
    public Users_24162118 login(String username, String password) {
        Users_24162118 user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password) && user.getStatus() == 1) {
            return user;
        }
        return null;
    }

    public boolean existsUsername(String username) {
        return userDAO.findByUsername(username) != null;
    }

    public boolean existsEmail(String email) {
        return userDAO.findByEmail(email) != null;
    }

    public void register(Users_24162118 user) {
        userDAO.insert(user);
    }

    // Kích hoạt tài khoản bằng OTP
    public boolean activate(String email, String otp) {
        Users_24162118 user = userDAO.findByEmail(email);
        if (user != null && user.getCode() != null && user.getCode().equals(otp)) {
            user.setStatus(1);
            user.setCode(null);
            userDAO.update(user);
            return true;
        }
        return false;
    }
}