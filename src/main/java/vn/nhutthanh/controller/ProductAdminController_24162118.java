package vn.nhutthanh.controller;



import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.nhutthanh.entity.Category_24162118;
import vn.nhutthanh.entity.Product_24162118;
import vn.nhutthanh.entity.Seller_24162118;
import vn.nhutthanh.service.CategoryService_24162118;
import vn.nhutthanh.service.ProductService_24162118;
import vn.nhutthanh.service.SellerService_24162118;

@WebServlet(urlPatterns = { "/admin/product", "/admin/product/form", "/admin/product/delete" })
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class ProductAdminController_24162118 extends HttpServlet {

    private static final int PAGE_SIZE = 5;
    private ProductService_24162118 productService = new ProductService_24162118();
    private CategoryService_24162118 categoryService = new CategoryService_24162118();
    private SellerService_24162118 sellerService = new SellerService_24162118();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.endsWith("/form")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                req.setAttribute("product", productService.findById(Integer.parseInt(idStr)));
            }
            req.setAttribute("categories", categoryService.findAll());
            req.setAttribute("sellers", sellerService.findAll());
            req.getRequestDispatcher("/WEB-INF/views/admin/product-form.jsp").forward(req, resp);

        } else if (uri.endsWith("/delete")) {
            productService.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/admin/product");

        } else {
            int page = 1;
            String pageStr = req.getParameter("page");
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }

            long total = productService.count();
            int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);
            List<Product_24162118> list = productService.findPage(page, PAGE_SIZE);

            // Map id -> tên để hiện tên thay vì số
            Map<Integer, String> catNames = new HashMap<>();
            for (Category_24162118 c : categoryService.findAll()) {
                catNames.put(c.getCategoryId(), c.getCategoryName());
            }
            Map<Integer, String> sellerNames = new HashMap<>();
            for (Seller_24162118 s : sellerService.findAll()) {
                sellerNames.put(s.getSellerId(), s.getSellername());
            }

            req.setAttribute("list", list);
            req.setAttribute("catNames", catNames);
            req.setAttribute("sellerNames", sellerNames);
            req.setAttribute("page", page);
            req.setAttribute("totalPages", totalPages);
            req.getRequestDispatcher("/WEB-INF/views/admin/product-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String idStr = req.getParameter("productId");
        Product_24162118 p = new Product_24162118();
        p.setProductName(req.getParameter("productName"));
        p.setProductCode(Long.parseLong(req.getParameter("productCode")));
        p.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
        p.setSellerId(Integer.parseInt(req.getParameter("sellerId")));
        p.setDescription(req.getParameter("description"));
        p.setPrice(Double.parseDouble(req.getParameter("price")));
        p.setAmount(Integer.parseInt(req.getParameter("amount")));
        p.setStock(Integer.parseInt(req.getParameter("amount")));
        p.setStatus(Integer.parseInt(req.getParameter("status")));

        // ===== Xử lý upload ảnh =====
        String fileName = saveImage(req);

        if (idStr == null || idStr.isEmpty()) {
            p.setImages(fileName != null ? fileName : "");
            p.setWishlist(0);
            p.setCreateDate(new Date());
            productService.insert(p);
        } else {
            Product_24162118 old = productService.findById(Integer.parseInt(idStr));
            p.setProductId(old.getProductId());
            p.setWishlist(old.getWishlist());
            p.setCreateDate(old.getCreateDate());
            // Không chọn ảnh mới thì giữ ảnh cũ
            p.setImages(fileName != null ? fileName : old.getImages());
            productService.update(p);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/product");
    }

    // Lưu file upload, trả về tên file (null nếu không chọn ảnh)
    private String saveImage(HttpServletRequest req) throws IOException, ServletException {
        Part part = req.getPart("imageFile");
        if (part == null || part.getSize() == 0) {
            return null;
        }

        // Thư mục uploads nằm trong project, tự tạo nếu chưa có
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