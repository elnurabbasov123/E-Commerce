package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import model.config.Config;
import model.entity.Admin;
import model.enums.Exceptions;
import model.exception.ObjectDontAddException;
import model.exception.ObjectNotFound;
import repository.inter.AdminRepository;

import java.util.List;
import java.util.Optional;

public class IAdminRepository extends Config implements AdminRepository {
    @Override
    public Optional<Admin> getById(long id) {
        Admin admin = getEntityManager().find(Admin.class, id);
        return Optional.ofNullable(admin);
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> findAll = getEntityManager()
                .createQuery("select a from admin a", Admin.class).getResultList();
        return findAll;
    }

    @Override
    public int add(Admin admin) {
        try {
            getEntityTransaction().begin();
            getEntityManager().persist(admin);
            getEntityTransaction().commit();
            return 1;
        } catch (Exception e) {
            getEntityTransaction().rollback();
            throw new ObjectDontAddException(Exceptions.OBJECT_DONT_ADD, "Admin");
        }
    }
}
