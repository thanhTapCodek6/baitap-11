package vn.nhutthanh.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.nhutthanh.entity.Users_24162118;
import vn.nhutthanh.util.JPAConfig_24162118;

public class UserDAO_24162118 {

    public void insert(Users_24162118 user) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Users_24162118 user) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Users_24162118 findByUsername(String username) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            TypedQuery<Users_24162118> q = em.createQuery(
                    "SELECT u FROM Users_24162118 u WHERE u.username = :username",
                    Users_24162118.class);
            q.setParameter("username", username);
            List<Users_24162118> list = q.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    public Users_24162118 findByEmail(String email) {
        EntityManager em = JPAConfig_24162118.getEntityManager();
        try {
            TypedQuery<Users_24162118> q = em.createQuery(
                    "SELECT u FROM Users_24162118 u WHERE u.email = :email",
                    Users_24162118.class);
            q.setParameter("email", email);
            List<Users_24162118> list = q.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }
}