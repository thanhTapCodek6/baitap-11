<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Giỏ hàng</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 12px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background: #ecf0f1; }
        td img { width: 60px; height: 60px; object-fit: cover; }
        .qty { width: 70px; padding: 4px; }
        .btn { padding: 6px 14px; color: white; border: none; border-radius: 3px; cursor: pointer; text-decoration: none; display: inline-block; }
        .upd { background: #2980b9; }
        .del { background: #c0392b; }
        .pay { background: #27ae60; font-size: 15px; }
        .error { color: red; }
        .total { text-align: right; margin-top: 15px; font-size: 18px; }
    </style>
</head>
<body>
    <h2>Giỏ hàng</h2>
    <c:if test="${not empty msg}"><p class="error">${msg}</p></c:if>

    <c:choose>
        <c:when test="${empty lines}">
            <p>Giỏ hàng đang trống. <a href="${pageContext.request.contextPath}/products">Tiếp tục mua sắm</a></p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Hình</th>
                    <th>Sản phẩm</th>
                    <th>Đơn giá</th>
                    <th>Số lượng (tối đa tồn kho)</th>
                    <th>Thành tiền</th>
                    <th>Xóa</th>
                </tr>
                <c:forEach var="l" items="${lines}">
                    <tr>
                        <td><img src="${l.product.images.startsWith('http') ? l.product.images : pageContext.request.contextPath.concat('/uploads/').concat(l.product.images)}" alt=""></td>
                        <td>${l.product.productName}</td>
                        <td><fmt:formatNumber value="${l.unitPrice}" pattern="#,##0"/> đ</td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/cart/update" style="display:inline">
                                <input type="hidden" name="cartItemId" value="${l.item.cartItemId}">
                                <input class="qty" type="number" name="quantity" value="${l.item.quantity}" min="0" max="${l.product.stock}">
                                <button class="btn upd" type="submit">Cập nhật</button>
                            </form>
                            <small>(còn ${l.product.stock})</small>
                        </td>
                        <td><fmt:formatNumber value="${l.total}" pattern="#,##0"/> đ</td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/cart/remove" style="display:inline"
                                  onsubmit="return confirm('Xóa sản phẩm khỏi giỏ?')">
                                <input type="hidden" name="cartItemId" value="${l.item.cartItemId}">
                                <button class="btn del" type="submit">Xóa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div class="total">
                Tổng cộng: <b><fmt:formatNumber value="${total}" pattern="#,##0"/> đ</b>
            </div>
            <p style="text-align:right">
                <a class="btn pay" href="${pageContext.request.contextPath}/checkout">Thanh toán (COD)</a>
            </p>
        </c:otherwise>
    </c:choose>
</body>
</html>
