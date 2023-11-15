package menu.impl;

import menu.helper.MenuHelper;
import menu.inter.Menu;
import model.entity.Admin;
import model.entity.Customer;

public class MainMenu extends MenuHelper implements Menu {

    @Override
    public void menu() {
        int option = mainMenuOption();
        switch (option){
            case 0:
                System.exit(0);
                break;
            case 1:
                isAdmin();
                adminMenu.menu();
               break;
            case 2:
                customerMenu.menu();
                break;
        }
    }
}
