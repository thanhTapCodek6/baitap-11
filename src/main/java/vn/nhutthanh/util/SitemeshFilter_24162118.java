package vn.nhutthanh.util;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*")
public class SitemeshFilter_24162118 extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        // Trang quản trị dùng decorator admin
    	builder.addDecoratorPath("/admin/*", "admin.jsp");
    	builder.addDecoratorPath("/*", "user.jsp");
    }
}