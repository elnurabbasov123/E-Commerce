package service.inter;

import model.entity.*;

import java.util.List;

public interface OrderService {
    int add(Order order);
    List<Order> findAll();
    int buyProduct(Order order, Cart cart, Company company, Customer customer, List<Product> products);
}
