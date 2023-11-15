package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.config.Config;
import model.entity.Category;
import model.enums.Exceptions;
import model.exception.ObjectDontAddException;
import model.exception.ObjectDontUpdated;
import model.exception.ObjectNotFound;
import repository.inter.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class ICategoryRepository extends Config implements CategoryRepository {
    @Override
    public int save(Category category) {
        try {
            getEntityTransaction().begin();
            getEntityManager().persist(category);
            getEntityTransaction().commit();
                return 1;
        } catch (Exception e) {
            getEntityTransaction().rollback();
            throw new ObjectDontAddException(Exceptions.OBJECT_DONT_ADD,"Category");
        }
    }

    @Override
    public List<Category> getAll() {
        return getEntityManager()
                .createNamedQuery("findAllCategory", Category.class).getResultList();
    }

    @Override
    public Optional<Category> findById(long id) {
        Category category = getEntityManager().find(Category.class, id);
        return Optional.ofNullable(category);
    }

    @Override
    public Optional<Category> findByName(String name) {
        Category singleResult = getEntityManager()
                .createNamedQuery("findByNameCategory", Category.class)
                .setParameter("name",name).getSingleResult();

        return Optional.ofNullable(singleResult);
    }

    @Override
    public int update(Category category) {
        try {
            getEntityTransaction().begin();
            getEntityManager().merge(category);
            getEntityTransaction().commit();
            return 1;
        } catch (Exception e) {
            getEntityTransaction().rollback();
            throw new ObjectDontUpdated(Exceptions.DONT_UPDATED,"Category");
        }
    }
}
