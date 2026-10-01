<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Category</title>
    <style>
        .box { max-width: 400px; }
        .box input, .box select { width: 100%; padding: 8px; margin: 6px 0 14px; box-sizing: border-box; }
        .box button { padding: 8px 20px; background: #c0392b; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <h2>${empty category ? 'Thêm Category' : 'Sửa Category'}</h2>
    <div class="box">
        <form method="post" enctype="multipart/form-data"
              action="${pageContext.request.contextPath}/admin/category/form">
            <input type="hidden" name="categoryId" value="${category.categoryId}">

            <label>Tên category</label>
            <input type="text" name="categoryName" value="${category.categoryName}" required>

            <label>Hình ảnh</label>
            <c:if test="${not empty category.images}">
                <div><img src="${category.images.startsWith('http') ? category.images : pageContext.request.contextPath.concat('/uploads/').concat(category.images)}"
                          width="100" alt=""></div>
            </c:if>
            <input type="file" name="imageFile" accept="image/*">

            <label>Trạng thái</label>
            <select name="status">
                <option value="1" ${category.status == 1 ? 'selected' : ''}>1 - Hoạt động</option>
                <option value="0" ${category.status == 0 && not empty category ? 'selected' : ''}>0 - Ẩn</option>
            </select>

            <button type="submit">Lưu</button>
            <a href="${pageContext.request.contextPath}/admin/category">Hủy</a>
        </form>
    </div>
</body>
</html>