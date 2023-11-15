package repository.impl;

import model.config.Config;
import model.entity.Cart;
import model.enums.Exceptions;
import model.exception.ObjectDontAddException;
import model.exception.ObjectDontUpdated;
import repository.inter.CartRepository;

public class ICartRepository extends Config implements CartRepository {
    @Override
    public int update(Cart cart) {
        try {
            getEntityTransaction().begin();
            getEntityManager().merge(cart);
            getEntityTransaction().commit();
            return 1;
        } catch (Exception e) {
            getEntityTransaction().rollback();
            throw new ObjectDontUpdated(Exceptions.DONT_UPDATED,"Cart");
        }
    }

    @Override
    public int save(Cart cart) {
        try {
            getEntityTransaction().begin();
            getEntityManager().persist(cart);
            getEntityTransaction().commit();
            return 1;
        }catch (Exception ex){
            getEntityTransaction().rollback();
            throw new ObjectDontAddException(Exceptions.OBJECT_DONT_ADD,"Cart");
        }
    }
}
