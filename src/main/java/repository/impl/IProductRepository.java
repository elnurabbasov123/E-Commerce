package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import model.config.Config;
import model.entity.Product;
import model.enums.Exceptions;
import model.exception.ObjectDontAddException;
import model.exception.ObjectDontUpdated;
import model.exception.ObjectNotFound;
import repository.inter.ProductRepository;

import java.util.List;
import java.util.Optional;

public class IProductRepository extends Config implements ProductRepository {
    @Override
    public int save(Product product) {
        try {
            getEntityTransaction().begin();
            entityManager.persist(product);
            getEntityTransaction().commit();
            return 1;
        } catch (Exception e) {
            getEntityTransaction().rollback();
            throw new ObjectDontAddException(Exceptions.OBJECT_DONT_ADD,"Product");
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> findAll = getEntityManager()
                .createNamedQuery("findAllProduct", Product.class).getResultList();
        return findAll;
    }

    @Override
    public Optional<Product> getById(long id) {
        Product singleResult = getEntityManager()
                .createNamedQuery("findByIdProduct", Product.class)
                .setParameter("id", id).getSingleResult();
        return Optional.of(singleResult);
    }

    @Override
    public Optional<Product> getByName(String name) {
        Product singleResult = getEntityManager()
                .createNamedQuery("findByNameProduct", Product.class)
                .setParameter("name", name).getSingleResult();
        return Optional.of(singleResult);
    }

    @Override
    public int update(Product product) {
        try {
            getEntityTransaction().begin();
            getEntityManager().merge(product);
            getEntityTransaction().commit();
            return 1;
        } catch (Exception e) {
            getEntityTransaction().rollback();
            throw new ObjectDontUpdated(Exceptions.DONT_UPDATED,"Product");
        }
    }
}