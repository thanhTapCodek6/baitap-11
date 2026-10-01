package vn.nhutthanh.service;

import java.util.List;

import vn.nhutthanh.dao.CategoryDAO_24162118;
import vn.nhutthanh.entity.Category_24162118;

public class CategoryService_24162118 {

    private CategoryDAO_24162118 dao = new CategoryDAO_24162118();

    public void insert(Category_24162118 c) { dao.insert(c); }
    public void update(Category_24162118 c) { dao.update(c); }
    public void delete(int id) { dao.delete(id); }
    public Category_24162118 findById(int id) { return dao.findById(id); }
    public List<Category_24162118> findAll() { return dao.findAll(); }
    public List<Category_24162118> findPage(int page, int pageSize) { return dao.findPage(page, pageSize); }
    public long count() { return dao.count(); }
}