package service.impl;
import lombok.RequiredArgsConstructor;
import model.entity.Cart;
import repository.impl.ICartRepository;
import repository.inter.CartRepository;
import service.inter.CartService;
@RequiredArgsConstructor
public class ICartService implements CartService {
    static CartRepository cartRepository = new ICartRepository();
    @Override
    public int update(Cart cart) {
       return cartRepository.update(cart);
    }

    @Override
    public int save(Cart cart) {
        return cartRepository.save(cart);
    }
}
