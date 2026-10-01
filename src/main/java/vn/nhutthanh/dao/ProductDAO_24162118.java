package vn.nhutthanh.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.nhutthanh.entity.Product_24162118;
import vn.nhutthanh.util.JPAConfig_24162118;

public class ProductDAO_24162118 {

    public void insert(Product_24162118 p) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(p);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Product_24162118 p) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(p);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Product_24162118 p = em.find(Product_24162118.class, id);
            if (p != null) {
                em.remove(p);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Product_24162118 findById(int id) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            return em.find(Product_24162118.class, id);
        } finally {
            em.close();
        }
    }

    // Câu 3: lấy tất cả sản phẩm, sắp xếp theo sellerId để nhóm theo cửa hàng
    public List<Product_24162118> findAllOrderBySeller() {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            TypedQuery<Product_24162118> q = em.createQuery(
                    "SELECT p FROM Product_24162118 p ORDER BY p.sellerId, p.productId",
                    Product_24162118.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    // Câu 5: phân trang, page bắt đầu từ 1
    public List<Product_24162118> findPage(int page, int pageSize) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            TypedQuery<Product_24162118> q = em.createQuery(
                    "SELECT p FROM Product_24162118 p ORDER BY p.productId",
                    Product_24162118.class);
            q.setFirstResult((page - 1) * pageSize);
            q.setMaxResults(pageSize);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public long count() {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            return em.createQuery("SELECT COUNT(p) FROM Product_24162118 p", Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}