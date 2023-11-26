package service.inter;

import model.entity.Customer;
import model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    int save();
    List<Product> getAll();
    Product getById();
    Product getByName();
    int update();
    void showAll();
    void addCommentAndStar(List<Product> products);
    void delete();
    void addToCart(Customer customer);
    void deleteFromCart(Customer customer);
    void buy(Customer customer);
    int setStar();
    String setComment();
}
