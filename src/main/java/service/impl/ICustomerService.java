package service.impl;

import lombok.RequiredArgsConstructor;
import menu.helper.FillHelper;
import model.entity.Customer;
import model.enums.Exceptions;
import model.exception.ObjectDontAddException;
import model.exception.OperationNotFound;
import model.exception.PasswordOrLoginNotFound;
import repository.impl.ICustomerRepository;
import repository.inter.CustomerRepository;
import service.inter.CustomerService;
import utility.InputUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class ICustomerService implements CustomerService {
    static CustomerRepository customerRepository = new ICustomerRepository();
    static FillHelper fillHelper = new FillHelper();
    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public Customer register() {
        Customer customer = fillHelper.fillCustomer();
        int added = customerRepository.add(customer);
        if (added == 1) {
            return customer;
        } else {
            throw new ObjectDontAddException(Exceptions.OBJECT_DONT_ADD, "Customer");
        }
    }

    @Override
    public Optional<Customer> getById(long id) {
        return customerRepository.getById(id);
    }

    @Override
    public Customer checkCustomer(String login, String password) {
     return customerRepository.getAll().stream()
                .filter(customer -> customer.getLogin().equals(login) && customer.getPassword().equals(password))
                .findFirst()
                .orElseThrow(()->new PasswordOrLoginNotFound(Exceptions.PASSWORD_OR_LOGIN_NOT_FOUND,"Customer"));
    }

    @Override
    public int update(Customer customer) {
        return customerRepository.update(customer);
    }

    @Override
    public Customer isCustomer() {
        String login = InputUtil.getInstance().inputString("Login:");
        String password = InputUtil.getInstance().inputString("Password:");

        return checkCustomer(login, password);
    }

    @Override
    public void increaseBalance(Customer customer) {
        Double v = InputUtil.getInstance().inputDouble("Amount:");
        customer.increaseAmount(new BigDecimal(v));

        customerRepository.update(customer);
    }

    @Override
    public Customer login() {
        int i = InputUtil.getInstance().inputInt("[0]->Exit\n" +
                "[1]->Login\n" +
                "[2]->Sign Up\n" +
                "Select:");
        Customer customer = null;
        switch (i) {
            case 0:
                System.exit(0);
                break;
            case 1:
                customer = isCustomer();
                break;
            case 2:
                customer = register();
                break;
            default:
                throw new OperationNotFound(Exceptions.OPERATION_NOT_FOUND);
        }
        return customer;
    }
}
