package repository.inter;

import model.entity.Cart;

public interface CartRepository {
    int update(Cart cart);
    int save(Cart cart);
}
