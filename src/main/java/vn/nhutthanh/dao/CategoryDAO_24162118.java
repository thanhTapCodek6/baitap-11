package vn.nhutthanh.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.nhutthanh.entity.Category_24162118;
import vn.nhutthanh.util.JPAConfig_24162118;

public class CategoryDAO_24162118 {

    public void insert(Category_24162118 c) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(c);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Category_24162118 c) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(c);
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
            Category_24162118 c = em.find(Category_24162118.class, id);
            if (c != null) {
                em.remove(c);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Category_24162118 findById(int id) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            return em.find(Category_24162118.class, id);
        } finally {
            em.close();
        }
    }

    public List<Category_24162118> findAll() {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            TypedQuery<Category_24162118> q = em.createQuery(
                    "SELECT c FROM Category_24162118 c", Category_24162118.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    // Phân trang: page bắt đầu từ 1
    public List<Category_24162118> findPage(int page, int pageSize) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            TypedQuery<Category_24162118> q = em.createQuery(
                    "SELECT c FROM Category_24162118 c ORDER BY c.categoryId",
                    Category_24162118.class);
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
            return em.createQuery("SELECT COUNT(c) FROM Category_24162118 c", Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}