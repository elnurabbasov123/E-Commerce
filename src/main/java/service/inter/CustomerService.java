package service.inter;

import model.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAll();
    int add(Customer customer);
    int update(Customer customer);
    Optional<Customer> getById(long id);
    Customer checkCustomer(String login,String password);
}
