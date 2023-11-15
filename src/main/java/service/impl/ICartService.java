package service.impl;

import model.entity.Cart;
import repository.impl.ICartRepository;
import repository.inter.CartRepository;
import service.inter.CartService;

public class ICartService implements CartService {
    CartRepository cartRepository=new ICartRepository();
    @Override
    public int update(Cart cart) {
       return cartRepository.update(cart);
    }

    @Override
    public int save(Cart cart) {
        return cartRepository.save(cart);
    }
}
