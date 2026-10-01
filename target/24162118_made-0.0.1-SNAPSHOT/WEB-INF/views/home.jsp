<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
    <h2>Chào mừng đến với Shop Online</h2>
    <c:if test="${not empty sessionScope.user}">
        <p>Xin chào, <b>${sessionScope.user.fullname}</b></p>
    </c:if>
</body>
</html>