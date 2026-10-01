<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title"/> - Shop Online</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background: #f5f5f5; }
        header { background: #2c3e50; padding: 12px 30px; }
        header a { color: white; text-decoration: none; margin-right: 20px; font-weight: bold; }
        header a:hover { color: #f1c40f; }
        main { max-width: 1000px; margin: 20px auto; background: white; padding: 20px; min-height: 400px; }
        footer { background: #2c3e50; color: white; text-align: center; padding: 12px; margin-top: 20px; }
    </style>
    <sitemesh:write property="head"/>
</head>
<body>
    <header>
        <a href="${pageContext.request.contextPath}/home">Trang Chủ</a>
        <a href="${pageContext.request.contextPath}/products">Sản phẩm</a>
        <c:if test="${not empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/cart">Giỏ hàng</a>
            <a href="${pageContext.request.contextPath}/orders">Đơn hàng</a>
        </c:if>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <a href="${pageContext.request.contextPath}/logout">Đăng xuất (${sessionScope.user.username})</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
            </c:otherwise>
        </c:choose>
        <c:if test="${sessionScope.user.roleId == 1}">
            <a href="${pageContext.request.contextPath}/admin/category">Trang quản trị</a>
        </c:if>
    </header>

    <main>
        <sitemesh:write property="body"/>
    </main>

    <footer>
        Họ tên: Cao Nhứt Thạnh &nbsp;|&nbsp; MSSV: 24162118 &nbsp;|&nbsp; Mã đề: 05
    </footer>
</body>
</html>