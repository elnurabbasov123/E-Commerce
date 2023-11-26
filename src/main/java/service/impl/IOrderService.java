package service.impl;
import model.entity.*;
import repository.impl.IOrderRepository;
import repository.inter.OrderRepository;
import service.inter.OrderService;
import java.util.List;
public class IOrderService implements OrderService {
    static OrderRepository orderRepository = new IOrderRepository();
    @Override
    public int add(Order order) {
        return orderRepository.add(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public int buyProduct(Order order, Cart cart, Company company, Customer customer, List<Product> products) {
        return orderRepository.buyProduct(order,cart,company,customer,products);
    }
}
