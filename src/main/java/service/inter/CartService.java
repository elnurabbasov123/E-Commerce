package service.inter;

import model.entity.Cart;

public interface CartService {
    int update(Cart cart);
    int save(Cart cart);
}
