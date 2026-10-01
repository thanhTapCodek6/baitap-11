<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Đăng ký</title>
    <style>
        .box { max-width: 350px; margin: 30px auto; }
        .box input { width: 100%; padding: 8px; margin: 6px 0 14px; box-sizing: border-box; }
        .box button { padding: 8px 20px; background: #2c3e50; color: white; border: none; cursor: pointer; }
        .error { color: red; }
    </style>
</head>
<body>
    <div class="box">
        <h2>Đăng ký</h2>
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/register">
            <label>Tên đăng nhập</label>
            <input type="text" name="username" required>
            <label>Email</label>
            <input type="email" name="email" required>
            <label>Họ tên</label>
            <input type="text" name="fullname" required>
            <label>Mật khẩu</label>
            <input type="password" name="password" maxlength="50" required>
            <label>Số điện thoại</label>
            <input type="text" name="phone" required>
            <button type="submit">Đăng ký</button>
        </form>
        <p>Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
    </div>
</body>
</html>