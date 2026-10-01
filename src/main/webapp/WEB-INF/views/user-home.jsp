<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Trang chủ User</title>
</head>
<body>
    <h2>Trang chủ User</h2>
    <p>Xin chào, <b>${sessionScope.user.fullname}</b></p>
    <p>Bạn có thể xem <a href="${pageContext.request.contextPath}/products">danh sách sản phẩm</a>.</p>
</body>
</html>