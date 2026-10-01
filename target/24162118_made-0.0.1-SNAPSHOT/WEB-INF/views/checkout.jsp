<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Thanh toán</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 12px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background: #ecf0f1; }
        .btn { padding: 8px 20px; color: white; border: none; border-radius: 3px; cursor: pointer; text-decoration: none; display: inline-block; }
        .ok { background: #27ae60; }
        .back { background: #7f8c8d; }
        .total { text-align: right; margin-top: 15px; font-size: 18px; }
        .pm { background: #f9f9f9; border: 1px solid #ddd; padding: 10px 14px; margin-top: 15px; }
    </style>
</head>
<body>
    <h2>Xác nhận đặt hàng</h2>

    <table>
        <tr>
            <th>Sản phẩm</th>
            <th>Đơn giá</th>
            <th>Số lượng</th>
            <th>Thành tiền</th>
        </tr>
        <c:forEach var="l" items="${lines}">
            <tr>
                <td>${l.product.productName}</td>
                <td><fmt:formatNumber value="${l.unitPrice}" pattern="#,##0"/> đ</td>
                <td>${l.item.quantity}</td>
                <td><fmt:formatNumber value="${l.total}" pattern="#,##0"/> đ</td>
            </tr>
        </c:forEach>
    </table>

    <div class="total">Tổng thanh toán: <b><fmt:formatNumber value="${total}" pattern="#,##0"/> đ</b></div>

    <div class="pm">
        Hình thức thanh toán: <b>Thanh toán khi nhận hàng (COD)</b>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/checkout" style="margin-top:15px">
        <a class="btn back" href="${pageContext.request.contextPath}/cart">Quay lại giỏ hàng</a>
        <button class="btn ok" type="submit">Đặt hàng</button>
    </form>
</body>
</html>
