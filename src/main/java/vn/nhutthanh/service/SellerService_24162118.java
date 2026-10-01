package vn.nhutthanh.service;

import java.util.List;

import vn.nhutthanh.dao.SellerDAO_24162118;
import vn.nhutthanh.entity.Seller_24162118;

public class SellerService_24162118 {

    private SellerDAO_24162118 dao = new SellerDAO_24162118();

    public Seller_24162118 findById(int id) { return dao.findById(id); }
    public List<Seller_24162118> findAll() { return dao.findAll(); }
}