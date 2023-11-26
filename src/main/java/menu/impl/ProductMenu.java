package menu.impl;
import lombok.RequiredArgsConstructor;
import menu.helper.ShowHelper;
import menu.inter.Menu;
import model.enums.Exceptions;
import model.exception.OperationNotFound;
import service.impl.IProductService;
import service.inter.ProductService;
@RequiredArgsConstructor
public class ProductMenu implements Menu {

    static ShowHelper showHelper = new ShowHelper();
    static ProductService productService = new IProductService();
    static AdminMenu adminMenu = new AdminMenu();
    @Override
    public void menu() {
        while (true) {
            int option =showHelper.productMenuOption();
            switch (option){
                case 0: System.exit(0); break;
                case 1:
                    productService.save();
                    break;
                case 2:
                    productService.showAll();
                    break;
                case 3:
                    productService.getById();
                    break;
                case 4:
                    productService.getByName();
                    break;
                case 5:
                    productService.update();
                    break;
                case 6:
                    productService.delete();
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
