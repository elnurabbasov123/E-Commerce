package menu.impl;

import menu.helper.MenuHelper;
import menu.inter.Menu;
import model.entity.Admin;
import model.enums.Exceptions;
import model.exception.OperationNotFound;
import service.impl.IAdminService;
import service.inter.AdminService;
public class AdminMenu extends MenuHelper implements Menu {

    static AdminService adminService =new IAdminService();

    static CategoryMenu categoryMenu = new CategoryMenu();
    static BrandMenu brandMenu = new BrandMenu();
    static ProductMenu productMenu=new ProductMenu();
    static MainMenu mainMenu=new MainMenu();
    @Override
    public void menu() {
        while (true){
            int option = adminMenuOption();
            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    categoryMenu.menu();
                    break;
                case 2:
                    brandMenu.menu();
                    break;
                case 3:
                    productMenu.menu();
                    break;
                case 4:
                    Admin admin = fillAdmin();
                    adminService.add(admin);
                    break;
                case 5:
                    mainMenu.menu();
                    break;
                default:
                    throw new OperationNotFound(Exceptions.OPERATION_NOT_FOUND);
            }
        }
    }
}
