<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title"/> - Quản trị</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background: #ecf0f1; }
        header { background: #c0392b; padding: 12px 30px; }
        header a { color: white; text-decoration: none; margin-right: 20px; font-weight: bold; }
        header a:hover { color: #f1c40f; }
        main { max-width: 1100px; margin: 20px auto; background: white; padding: 20px; min-height: 400px; }
        footer { background: #c0392b; color: white; text-align: center; padding: 12px; margin-top: 20px; }
    </style>
    <sitemesh:write property="head"/>
</head>
<body>
    <header>
        <a href="${pageContext.request.contextPath}/home">Trang Chủ</a>
        <a href="${pageContext.request.contextPath}/products">Sản phẩm</a>
        <a href="${pageContext.request.contextPath}/admin/category">Quản lý Category</a>
        <a href="${pageContext.request.contextPath}/admin/product">Quản lý Product</a>
        <a href="${pageContext.request.contextPath}/logout">Đăng xuất (${sessionScope.user.username})</a>
    </header>

    <main>
        <sitemesh:write property="body"/>
    </main>

    <footer>
        Họ tên: Cao Nhứt Thạnh &nbsp;|&nbsp; MSSV: 24162118 &nbsp;|&nbsp; Mã đề: 05
    </footer>
</body>
</html>