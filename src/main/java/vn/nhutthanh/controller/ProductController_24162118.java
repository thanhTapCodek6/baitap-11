package vn.nhutthanh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet(urlPatterns = { "/products" })
public class ProductController_24162118 extends HttpServlet {

    private ProductService_24162118 productService = new ProductService_24162118();
    private CategoryService_24162118 categoryService = new CategoryService_24162118();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Map: sellerId -> danh sách sản phẩm của seller đó (giữ đúng thứ tự)
        Map<Integer, List<ProductView_24162118>> groups = new LinkedHashMap<>();

        for (Product_24162118 p : productService.findAllOrderBySeller()) {
            Category_24162118 c = categoryService.findById(p.getCategoryId());
            String catName = (c != null) ? c.getCategoryName() : "";

            groups.computeIfAbsent(p.getSellerId(), k -> new ArrayList<>())
                  .add(new ProductView_24162118(p, catName));
        }

        req.setAttribute("groups", groups);
        req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
    }
}