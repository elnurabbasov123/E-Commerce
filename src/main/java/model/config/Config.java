package model.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Config {
    protected final EntityManagerFactory entityManagerFactory;
    protected final EntityManager entityManager;
    protected final EntityTransaction entityTransaction;

    protected Config(){
        entityManagerFactory= Persistence.createEntityManagerFactory("postgres");
         entityManager = entityManagerFactory.createEntityManager();
         entityTransaction=entityManager.getTransaction();
    }

    protected EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected EntityTransaction getEntityTransaction() {
        return entityTransaction;
    }
}
