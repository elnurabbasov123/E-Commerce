package repository.inter;

import model.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    List<Customer> getAll();
    int add(Customer customer);
    Optional<Customer> getById(long id);
    int update(Customer customer);
}
