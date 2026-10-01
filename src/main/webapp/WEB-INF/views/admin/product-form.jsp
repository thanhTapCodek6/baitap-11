<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Product</title>
    <style>
        .box { max-width: 450px; }
        .box input, .box select, .box textarea { width: 100%; padding: 8px; margin: 6px 0 14px; box-sizing: border-box; }
        .box button { padding: 8px 20px; background: #c0392b; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <h2>${empty product ? 'Thêm Product' : 'Sửa Product'}</h2>
    <div class="box">
        <form method="post" enctype="multipart/form-data"
              action="${pageContext.request.contextPath}/admin/product/form">
            <input type="hidden" name="productId" value="${product.productId}">

            <label>Tên sản phẩm</label>
            <input type="text" name="productName" value="${product.productName}" required>

            <label>Mã sản phẩm</label>
            <input type="number" name="productCode" value="${product.productCode}" required>

            <label>Danh mục</label>
            <select name="categoryId">
                <c:forEach var="c" items="${categories}">
                    <option value="${c.categoryId}" ${product.categoryId == c.categoryId ? 'selected' : ''}>
                        ${c.categoryName}
                    </option>
                </c:forEach>
            </select>

            <label>Cửa hàng</label>
            <select name="sellerId">
                <c:forEach var="s" items="${sellers}">
                    <option value="${s.sellerId}" ${product.sellerId == s.sellerId ? 'selected' : ''}>
                        ${s.sellername}
                    </option>
                </c:forEach>
            </select>

            <label>Giá</label>
            <input type="number" step="any" name="price" value="${product.price}" required>

            <label>Amount</label>
            <input type="number" name="amount" value="${product.amount}" required>

            <label>Hình ảnh</label>
            <c:if test="${not empty product.images}">
                <div><img src="${product.images.startsWith('http') ? product.images : pageContext.request.contextPath.concat('/uploads/').concat(product.images)}"
                          width="100" alt=""></div>
            </c:if>
            <input type="file" name="imageFile" accept="image/*">

            <label>Description</label>
            <textarea name="description" rows="3">${product.description}</textarea>

            <label>Trạng thái</label>
            <select name="status">
                <option value="1" ${product.status == 1 ? 'selected' : ''}>1 - Đang bán</option>
                <option value="0" ${product.status == 0 && not empty product ? 'selected' : ''}>0 - Ẩn</option>
            </select>

            <button type="submit">Lưu</button>
            <a href="${pageContext.request.contextPath}/admin/product">Hủy</a>
        </form>
    </div>
</body>
</html>