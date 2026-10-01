<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Chi tiết sản phẩm</title>
    <style>
        .detail { display: flex; gap: 25px; }
        .detail img { width: 280px; height: 280px; object-fit: cover; }
        .info p { margin: 8px 0; }
        .back { display: inline-block; margin-top: 15px; color: #2c3e50; text-decoration: none; font-weight: bold; }
    </style>
</head>
<body>
    <h2>Chi tiết sản phẩm</h2>

    <div class="detail">
        <img src="${pv.product.images.startsWith('http') ? pv.product.images : pageContext.request.contextPath.concat('/uploads/').concat(pv.product.images)}">
        <div class="info">
            <p>Tên sản phẩm: <b>${pv.product.productName}</b></p>
            <p>Mã sản phẩm: ${pv.product.productCode}</p>
            <p>Danh mục: ${pv.categoryName}</p>
            <p>Giá: <fmt:formatNumber value="${pv.product.price}" pattern="#,##0"/> đ</p>
            <p>Amount: ${pv.product.amount}</p>
            <p>Description: ${pv.product.description}</p>
            <form method="post" action="${pageContext.request.contextPath}/cart/add">
                <input type="hidden" name="productId" value="${pv.product.productId}">
                <input type="number" name="quantity" value="1" min="1" max="${pv.product.stock}" style="width:60px">
                <button type="submit">Thêm vào giỏ</button>
            </form>
        </div>
    </div>

    <a class="back" href="${pageContext.request.contextPath}/products">&larr; Quay lại danh sách</a>
</body>
</html>