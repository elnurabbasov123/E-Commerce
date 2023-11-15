package service.impl;

import menu.impl.MainMenu;
import menu.inter.Menu;
import service.inter.ManagmentService;

public class IManagmentService implements ManagmentService {
    static Menu mainMenu=new MainMenu();
    @Override
    public void manage() {
        mainMenu.menu();
    }
}
