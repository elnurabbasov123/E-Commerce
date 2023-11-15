package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import model.config.Config;
import model.entity.Customer;
import model.enums.Exceptions;
import model.exception.ObjectDontAddException;
import model.exception.ObjectDontUpdated;
import repository.inter.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class ICustomerRepository extends Config implements CustomerRepository {
    @Override
    public List<Customer> getAll() {
        List<Customer> findAll = getEntityManager()
                .createNamedQuery("findAllCustomer", Customer.class).getResultList();
        return findAll;
    }

    @Override
    public int add(Customer customer) {
        try{
            getEntityTransaction().begin();
            getEntityManager().persist(customer);
            getEntityTransaction().commit();
            return 1;
        }catch (Exception ex){
            getEntityTransaction().rollback();
            throw new ObjectDontAddException(Exceptions.OBJECT_DONT_ADD,"Customer");
        }
    }

    @Override
    public Optional<Customer> getById(long id) {
        Customer singleResult = getEntityManager().find( Customer.class,id);
        return Optional.ofNullable(singleResult);
    }

    @Override
    public int update(Customer customer) {
        try{
            getEntityTransaction().begin();
            getEntityManager().merge(customer);
            getEntityTransaction().commit();
            return 1;
        }catch (Exception ex){
            getEntityTransaction().rollback();
            throw new ObjectDontUpdated(Exceptions.DONT_UPDATED,"Customer");
        }
    }
}
