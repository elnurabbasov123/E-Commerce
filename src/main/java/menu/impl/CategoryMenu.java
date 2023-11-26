package menu.impl;
import lombok.RequiredArgsConstructor;
import menu.helper.FillHelper;
import menu.helper.ShowHelper;
import menu.inter.Menu;
import model.entity.Category;
import model.enums.Exceptions;
import model.exception.OperationNotFound;
import service.impl.ICategoryService;
import service.inter.CategoryService;
@RequiredArgsConstructor
public class CategoryMenu implements Menu {
    static CategoryService categoryService = new ICategoryService();
    static FillHelper fillHelper = new FillHelper();
    static ShowHelper showHelper = new ShowHelper();
    static AdminMenu adminMenu = new AdminMenu();

    @Override
    public void menu() {
        while (true) {
            int option=showHelper.categoryMenuOption();
            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    Category category = fillHelper.fillCategory();
                    categoryService.save(category);
                    break;
                case 2:
                    categoryService.getAll().stream().forEach(System.out::println);
                    break;
                case 3:
                    categoryService.findById();
                    break;
                case 4:
                    categoryService.findByName();
                    break;
                case 5:
                    categoryService.update();
                    break;
                case 6:
                    categoryService.delete();
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
