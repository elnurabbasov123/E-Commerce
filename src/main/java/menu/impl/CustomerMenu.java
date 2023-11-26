package menu.impl;
import lombok.RequiredArgsConstructor;
import model.helper.ShowHelper;
import menu.inter.Menu;
import model.entity.Customer;
import model.enums.Exceptions;
import model.exception.OperationNotFound;
import service.impl.ICustomerService;
import service.impl.IProductService;
import service.inter.CustomerService;
import service.inter.ProductService;

@RequiredArgsConstructor
public class CustomerMenu  implements Menu {
    static MainMenu mainMenu = new MainMenu();
    static CustomerService customerService = new ICustomerService();
    static ShowHelper showHelper = new ShowHelper();
    static ProductService productService = new IProductService();
    @Override
    public void menu() {
        Customer customer = customerService.login();
        while (true) {
            int option = showHelper.customerMenuOption();

            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    productService.showAll();
                    break;
                case 2:
                    productService.addToCart(customer);
                    break;
                case 3:
                    productService.deleteFromCart(customer);
                    break;
                case 4:
                    productService.buy(customer);
                    break;
                case 5:
                    customerService.increaseBalance(customer);
                    break;
                case 6:
                    mainMenu.menu();
                    break;
                default:
                    throw new OperationNotFound(Exceptions.OPERATION_NOT_FOUND);
            }
        }
    }
}

