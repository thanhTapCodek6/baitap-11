<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Quản lý Category</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 12px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background: #ecf0f1; }
        td img { width: 60px; height: 60px; object-fit: cover; }
        .btn { padding: 5px 12px; color: white; text-decoration: none; border-radius: 3px; }
        .add { background: #27ae60; }
        .edit { background: #2980b9; }
        .del { background: #c0392b; }
        .paging { margin-top: 15px; }
        .paging a { padding: 5px 10px; border: 1px solid #ccc; text-decoration: none; color: #333; margin-right: 4px; }
        .paging a.active { background: #c0392b; color: white; }
    </style>
</head>
<body>
    <h2>Quản lý Category</h2>
    <a class="btn add" href="${pageContext.request.contextPath}/admin/category/form">+ Thêm mới</a>

    <table>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Hình</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
        <c:forEach var="c" items="${list}">
            <tr>
                <td>${c.categoryId}</td>
                <td>${c.categoryName}</td>
                <td><img src="${c.images.startsWith('http') ? c.images : pageContext.request.contextPath.concat('/uploads/').concat(c.images)}" alt=""></td>
                <td>${c.status}</td>
                <td>
                    <a class="btn edit" href="${pageContext.request.contextPath}/admin/category/form?id=${c.categoryId}">Sửa</a>
                    <a class="btn del" href="${pageContext.request.contextPath}/admin/category/delete?id=${c.categoryId}"
                       onclick="return confirm('Xóa category này?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="paging">
        <c:forEach begin="1" end="${totalPages}" var="i">
            <a class="${i == page ? 'active' : ''}"
               href="${pageContext.request.contextPath}/admin/category?page=${i}">${i}</a>
        </c:forEach>
    </div>
</body>
</html>