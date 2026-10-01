<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Sản phẩm</title>
    <style>
        .seller { margin-bottom: 30px; }
        .seller h3 { background: #ecf0f1; padding: 8px 12px; border-left: 4px solid #2c3e50; }
        .item { display: flex; gap: 15px; border-bottom: 1px solid #ddd; padding: 12px 0; }
        .item img { width: 120px; height: 120px; object-fit: cover; }
        .info p { margin: 4px 0; }
        .info a { color: #2c3e50; font-weight: bold; text-decoration: none; }
        .info a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <h2>Danh sách sản phẩm</h2>

    <c:forEach var="entry" items="${groups}">
        <div class="seller">
            <h3>Mã cửa hàng: ${entry.key}</h3>

            <c:forEach var="pv" items="${entry.value}">
                <div class="item">
                    <img src="${pv.product.images.startsWith('http') ? pv.product.images : pageContext.request.contextPath.concat('/uploads/').concat(pv.product.images)}">
                    <div class="info">
                        <p>Tên sản phẩm:
                            <a href="${pageContext.request.contextPath}/product-detail?id=${pv.product.productId}">
                                ${pv.product.productName}
                            </a>
                        </p>
                        <p>Mã sản phẩm: ${pv.product.productCode}</p>
                        <p>Danh mục: ${pv.categoryName}</p>
                        <p>Giá: <fmt:formatNumber value="${pv.product.price}" pattern="#,##0"/> đ</p>
                        <p>Amount: ${pv.product.amount}</p>
                        <form method="post" action="${pageContext.request.contextPath}/cart/add">
                            <input type="hidden" name="productId" value="${pv.product.productId}">
                            <input type="number" name="quantity" value="1" min="1" max="${pv.product.stock}" style="width:60px">
                            <button type="submit">Thêm vào giỏ</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:forEach>
</body>
</html>