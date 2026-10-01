package vn.nhutthanh.service;

import java.util.List;

import vn.nhutthanh.dao.ProductDAO_24162118;
import vn.nhutthanh.entity.Product_24162118;

public class ProductService_24162118 {

    private ProductDAO_24162118 dao = new ProductDAO_24162118();

    public void insert(Product_24162118 p) { dao.insert(p); }
    public void update(Product_24162118 p) { dao.update(p); }
    public void delete(int id) { dao.delete(id); }
    public Product_24162118 findById(int id) { return dao.findById(id); }
    public List<Product_24162118> findAllOrderBySeller() { return dao.findAllOrderBySeller(); }
    public List<Product_24162118> findPage(int page, int pageSize) { return dao.findPage(page, pageSize); }
    public long count() { return dao.count(); }
}