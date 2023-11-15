package repository.impl;

import jakarta.persistence.*;
import model.config.Config;
import model.entity.Brand;
import model.enums.Exceptions;
import model.exception.ObjectDontAddException;
import model.exception.ObjectDontUpdated;
import model.exception.ObjectNotFound;
import repository.inter.BrandRepository;

import java.util.List;
import java.util.Optional;

public class IBrandRepository extends Config implements BrandRepository {
    @Override
    public int save(Brand brand) {
        try{
            getEntityTransaction().begin();
            getEntityManager().persist(brand);
            getEntityTransaction().commit();
            return 1;
        }catch (Exception ex){
            getEntityTransaction().rollback();
            throw new ObjectDontAddException(Exceptions.OBJECT_DONT_ADD,"Brand");
        }
    }

    @Override
    public List<Brand> getAll() {
        List<Brand> findAll = getEntityManager()
                .createNamedQuery("findAllBrand", Brand.class).getResultList();
        return findAll;
    }

    @Override
    public Optional<Brand> findById(long id) {
        Brand brand = getEntityManager().find(Brand.class, id);
        return Optional.ofNullable(brand);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        Brand singleResult = getEntityManager()
                .createNamedQuery("findByNameBrand", Brand.class)
                .setParameter("name", name).getSingleResult();
        return Optional.of(singleResult);
    }

    @Override
    public int update(Brand brand) {
        try {
            getEntityTransaction().begin();
            getEntityManager().merge(brand);
            getEntityTransaction().commit();
            return 1;
        }catch (Exception ex){
            getEntityTransaction().rollback();
            throw new ObjectDontUpdated(Exceptions.DONT_UPDATED,"Brand");
        }
    }
}
