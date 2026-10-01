package vn.nhutthanh.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig_24162118 {

    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("ShopOnlinePU");

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}