package menu.impl;

import menu.helper.MenuHelper;
import menu.inter.Menu;
import model.entity.Customer;
import model.enums.Exceptions;
import model.exception.OperationNotFound;
public class CustomerMenu extends MenuHelper implements Menu {
    MainMenu mainMenu = new MainMenu();

    @Override
    public void menu() {
        Customer customer = loginCustomer();
        while (true) {
            int option = customerMenuOption();

            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    showAllProduct();
                    break;
                case 2:
                    addProductToCart(customer);
                    break;
                case 3:
                    deleteProductToCart(customer);
                    break;
                case 4:
                    buyProduct(customer);
                    break;
                case 5:
                    increaseBalance(customer);
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

