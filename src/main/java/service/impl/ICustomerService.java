package service.impl;

import model.entity.Customer;
import model.enums.Exceptions;
import model.exception.PasswordOrLoginNotFound;
import repository.impl.ICustomerRepository;
import repository.inter.CustomerRepository;
import service.inter.CustomerService;

import java.util.List;
import java.util.Optional;

public class ICustomerService implements CustomerService {
    CustomerRepository customerRepository=new ICustomerRepository();
    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public int add(Customer customer) {
        return customerRepository.add(customer);
    }

    @Override
    public Optional<Customer> getById(long id) {
        return customerRepository.getById(id);
    }

    @Override
    public Customer checkCustomer(String login, String password) {
     return getAll().stream()
                .filter(customer -> customer.getLogin().equals(login) && customer.getPassword().equals(password))
                .findFirst()
                .orElseThrow(()->new PasswordOrLoginNotFound(Exceptions.PASSWORD_OR_LOGIN_NOT_FOUND,"Customer"));
    }

    @Override
    public int update(Customer customer) {
        return customerRepository.update(customer);
    }
}
