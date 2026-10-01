<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Xác nhận OTP</title>
    <style>
        .box { max-width: 350px; margin: 30px auto; }
        .box input { width: 100%; padding: 8px; margin: 6px 0 14px; box-sizing: border-box; }
        .box button { padding: 8px 20px; background: #2c3e50; color: white; border: none; cursor: pointer; }
        .error { color: red; }
    </style>
</head>
<body>
    <div class="box">
        <h2>Xác nhận OTP</h2>
        <p>Mã OTP đã được gửi tới: <b>${email}</b></p>
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/verify">
            <input type="hidden" name="email" value="${email}">
            <label>Nhập mã OTP</label>
            <input type="text" name="otp" required>
            <button type="submit">Kích hoạt</button>
        </form>
    </div>
</body>
</html>