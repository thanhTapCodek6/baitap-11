package vn.nhutthanh.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.nhutthanh.entity.Seller_24162118;
import vn.nhutthanh.util.JPAConfig_24162118;

public class SellerDAO_24162118 {

    public Seller_24162118 findById(int id) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            return em.find(Seller_24162118.class, id);
        } finally {
            em.close();
        }
    }

    public List<Seller_24162118> findAll() {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            TypedQuery<Seller_24162118> q = em.createQuery(
                    "SELECT s FROM Seller_24162118 s", Seller_24162118.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}