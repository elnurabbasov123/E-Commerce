package repository.impl;

import menu.helper.MenuHelper;
import model.config.Config;
import model.entity.*;
import model.enums.Exceptions;
import model.exception.ObjectDontAddException;
import model.exception.OrderDontAdoptedException;
import repository.inter.OrderRepository;
import java.util.List;

public class IOrderRepository extends Config implements OrderRepository {
    MenuHelper helper=new MenuHelper();
    @Override
    public int add(Order order) {
        try {
            getEntityTransaction().begin();
            getEntityManager().persist(order);
            getEntityTransaction().commit();
        return 1;
        }catch (Exception ex){
            getEntityTransaction().rollback();
            throw new ObjectDontAddException(Exceptions.OBJECT_DONT_ADD,"Order");
        }
    }

    @Override
    public int buyProduct(Order order, Cart cart, Company company, Customer customer, List<Product> products) {
        try {
            getEntityTransaction().begin();
            helper.addCommentAndStar(products);
            getEntityManager().merge(order);
            getEntityManager().merge(company);
            getEntityManager().merge(cart);
            getEntityManager().merge(customer);
            getEntityTransaction().commit();
            return 1;
        }catch (Exception ex){
            getEntityTransaction().rollback();
            throw new OrderDontAdoptedException(customer.getName(),customer.getSurname(),Exceptions.DONT_ADOPTED);
        }
    }

    @Override
    public List<Order> findAll() {
      return   getEntityManager().createQuery("select o from _order o").getResultList();
    }
}
