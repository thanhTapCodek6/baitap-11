package vn.nhutthanh.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.nhutthanh.entity.Category_24162118;
import vn.nhutthanh.entity.Product_24162118;
import vn.nhutthanh.entity.ProductView_24162118;
import vn.nhutthanh.service.CategoryService_24162118;
import vn.nhutthanh.service.ProductService_24162118;

@WebServlet(urlPatterns = { "/product-detail" })
public class ProductDetailController_24162118 extends HttpServlet {

    private ProductService_24162118 productService = new ProductService_24162118();
    private CategoryService_24162118 categoryService = new CategoryService_24162118();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        Product_24162118 p = productService.findById(Integer.parseInt(idStr));
        if (p == null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        Category_24162118 c = categoryService.findById(p.getCategoryId());
        String catName = (c != null) ? c.getCategoryName() : "";

        req.setAttribute("pv", new ProductView_24162118(p, catName));
        req.getRequestDispatcher("/WEB-INF/views/product-detail.jsp").forward(req, resp);
    }
}