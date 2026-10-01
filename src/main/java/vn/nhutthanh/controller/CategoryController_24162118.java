package vn.nhutthanh.controller;

import java.io.IOException;
import java.util.List;


import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.nhutthanh.entity.Category_24162118;
import vn.nhutthanh.service.CategoryService_24162118;

@WebServlet(urlPatterns = { "/admin/category", "/admin/category/form", "/admin/category/delete" })
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class CategoryController_24162118 extends HttpServlet {

    private static final int PAGE_SIZE = 5;
    private CategoryService_24162118 service = new CategoryService_24162118();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.endsWith("/form")) {
            // Form thêm (không có id) hoặc sửa (có id)
            String idStr = req.getParameter("id");
            if (idStr != null) {
                req.setAttribute("category", service.findById(Integer.parseInt(idStr)));
            }
            req.getRequestDispatcher("/WEB-INF/views/admin/category-form.jsp").forward(req, resp);

        } else if (uri.endsWith("/delete")) {
            service.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/admin/category");

        } else {
            // Danh sách có phân trang
            int page = 1;
            String pageStr = req.getParameter("page");
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }

            long total = service.count();
            int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);
            List<Category_24162118> list = service.findPage(page, PAGE_SIZE);

            req.setAttribute("list", list);
            req.setAttribute("page", page);
            req.setAttribute("totalPages", totalPages);
            req.getRequestDispatcher("/WEB-INF/views/admin/category-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String idStr = req.getParameter("categoryId");
        Category_24162118 c = new Category_24162118();
        c.setCategoryName(req.getParameter("categoryName"));
        c.setStatus(Integer.parseInt(req.getParameter("status")));

        String fileName = saveImage(req);

        if (idStr == null || idStr.isEmpty()) {
            c.setImages(fileName != null ? fileName : "");
            service.insert(c);
        } else {
            Category_24162118 old = service.findById(Integer.parseInt(idStr));
            c.setCategoryId(old.getCategoryId());
            // Không chọn ảnh mới thì giữ ảnh cũ
            c.setImages(fileName != null ? fileName : old.getImages());
            service.update(c);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/category");
    }

    // Lưu file upload, trả về tên file (null nếu không chọn ảnh)
    private String saveImage(HttpServletRequest req) throws IOException, ServletException {
        Part part = req.getPart("imageFile");
        if (part == null || part.getSize() == 0) {
            return null;
        }

        String uploadPath = req.getServletContext().getRealPath("/uploads");
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String original = part.getSubmittedFileName();
        String fileName = System.currentTimeMillis() + "_" + original.replaceAll("\\s+", "_");
        Files.copy(part.getInputStream(), new File(dir, fileName).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
}