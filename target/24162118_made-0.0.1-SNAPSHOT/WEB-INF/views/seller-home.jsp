<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Trang chủ Seller</title>
</head>
<body>
    <h2>Trang chủ Seller</h2>
    <p>Xin chào, <b>${sessionScope.user.fullname}</b></p>
    <p>Mã cửa hàng của bạn: <b>${sessionScope.user.sellerid}</b></p>
    <p>Xem <a href="${pageContext.request.contextPath}/products">danh sách sản phẩm</a>.</p>
</body>
</html>