package menu.impl;
import menu.helper.MenuHelper;
import menu.inter.Menu;
import model.enums.Exceptions;
import model.exception.OperationNotFound;
public class ProductMenu extends MenuHelper implements Menu {

    @Override
    public void menu() {
        while (true) {
            int option =productMenuOption();
            switch (option){
                case 0: System.exit(0); break;
                case 1:
                    saveProduct();
                    break;
                case 2:
                    showAllProduct();
                    break;
                case 3:
                    findByIdProduct();
                    break;
                case 4:
                   findByNameProduct();
                    break;
                case 5:
                    updateProduct();
                    break;
                case 6:
                   deleteProduct();
                    break;
                case 7:
                    adminMenu.menu();
                    break;
                default:
                    throw new OperationNotFound(Exceptions.OPERATION_NOT_FOUND);
            }
        }
    }
}
