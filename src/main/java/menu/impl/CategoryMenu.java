package menu.impl;
import menu.helper.MenuHelper;
import menu.inter.Menu;
import model.entity.Category;
import model.enums.Exceptions;
import model.exception.OperationNotFound;
import service.impl.ICategoryService;
import service.inter.CategoryService;
public class CategoryMenu extends MenuHelper implements Menu {
    static CategoryService categoryService = new ICategoryService();

    @Override
    public void menu() {
        while (true) {
            int option=categoryMenuOption();
            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    Category category = fillCategory();
                    categoryService.save(category);
                    break;
                case 2:
                    categoryService.getAll().stream().forEach(System.out::println);
                    break;
                case 3:
                    findByIdCategory();
                    break;
                case 4:
                    findByNameCategory();
                    break;
                case 5:
                    updateCategory();
                    break;
                case 6:
                    deleteCategory();
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
