package service.inter;

import model.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAll();
    Customer register();
    int update(Customer customer);
    Optional<Customer> getById(long id);
    Customer checkCustomer(String login,String password);
    Customer isCustomer();
    void increaseBalance(Customer customer);
    Customer login();
}
