package menu.impl;
import lombok.RequiredArgsConstructor;
import menu.helper.ShowHelper;
import menu.inter.Menu;
import service.impl.IAdminService;
import service.inter.AdminService;
@RequiredArgsConstructor
public class MainMenu implements Menu {
    static ShowHelper showHelper = new ShowHelper();
    static AdminMenu adminMenu = new AdminMenu();
    static AdminService adminService = new IAdminService();
    static CustomerMenu customerMenu = new CustomerMenu();
    @Override
    public void menu() {
        int option = showHelper.mainMenuOption();
        switch (option){
            case 0:
                System.exit(0);
                break;
            case 1:
                adminService.isAdmin();
                adminMenu.menu();
               break;
            case 2:
                customerMenu.menu();
                break;
        }
    }
}
