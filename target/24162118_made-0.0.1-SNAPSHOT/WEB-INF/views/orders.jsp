<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Lịch sử đặt hàng</title>
    <style>
        .tabs { margin: 10px 0 20px; }
        .tabs a { display: inline-block; padding: 6px 12px; border: 1px solid #ccc; margin: 0 4px 6px 0; text-decoration: none; color: #333; border-radius: 3px; }
        .tabs a.active { background: #2c3e50; color: white; border-color: #2c3e50; }
        .order { border: 1px solid #ddd; margin-bottom: 18px; }
        .order-head { background: #ecf0f1; padding: 8px 12px; display: flex; justify-content: space-between; flex-wrap: wrap; gap: 8px; }
        .status { font-weight: bold; color: #c0392b; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border-top: 1px solid #ddd; padding: 6px 12px; text-align: left; }
        .order-total { text-align: right; padding: 8px 12px; border-top: 1px solid #ddd; }
        .success { color: #27ae60; font-weight: bold; }
    </style>
</head>
<body>
    <h2>Lịch sử đặt hàng</h2>
    <c:if test="${param.placed == 1}">
        <p class="success">Đặt hàng thành công! Bạn sẽ thanh toán khi nhận hàng (COD).</p>
    </c:if>

    <div class="tabs">
        <a href="${pageContext.request.contextPath}/orders" class="${empty current ? 'active' : ''}">Tất cả</a>
        <c:forEach var="s" items="${statusLabels}">
            <a href="${pageContext.request.contextPath}/orders?status=${s.key}"
               class="${current == s.key ? 'active' : ''}">${s.value}</a>
        </c:forEach>
    </div>

    <c:if test="${empty orders}">
        <p>Không có đơn hàng nào.</p>
    </c:if>

    <c:forEach var="o" items="${orders}">
        <div class="order">
            <div class="order-head">
                <span>Mã đơn: <b>${o.cart.cartId}</b></span>
                <span>Ngày đặt: <fmt:formatDate value="${o.cart.buyDate}" pattern="dd/MM/yyyy HH:mm"/></span>
                <span class="status">${statusLabels[o.cart.status]}</span>
            </div>
            <table>
                <c:forEach var="l" items="${o.lines}">
                    <tr>
                        <td>${l.product.productName}</td>
                        <td><fmt:formatNumber value="${l.unitPrice}" pattern="#,##0"/> đ</td>
                        <td>x ${l.item.quantity}</td>
                        <td><fmt:formatNumber value="${l.total}" pattern="#,##0"/> đ</td>
                    </tr>
                </c:forEach>
            </table>
            <div class="order-total">
                Thanh toán: COD &nbsp;|&nbsp; Tổng: <b><fmt:formatNumber value="${o.total}" pattern="#,##0"/> đ</b>
            </div>
        </div>
    </c:forEach>
</body>
</html>
