package service.impl;

import lombok.RequiredArgsConstructor;
import menu.impl.MainMenu;
import menu.inter.Menu;
import service.inter.ManagmentService;
@RequiredArgsConstructor
public class IManagmentService implements ManagmentService {
    static Menu mainMenu = new MainMenu();
    @Override
    public void manage() {
        mainMenu.menu();
    }
}
