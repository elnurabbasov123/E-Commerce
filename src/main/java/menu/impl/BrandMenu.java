package menu.impl;
import lombok.RequiredArgsConstructor;
import model.helper.ShowHelper;
import menu.inter.Menu;
import model.enums.Exceptions;
import model.exception.OperationNotFound;
import service.impl.IBrandService;
import service.inter.BrandService;
@RequiredArgsConstructor
public class BrandMenu implements Menu {
    static BrandService brandService = new IBrandService();
    static ShowHelper showHelper = new ShowHelper();
    static AdminMenu adminMenu = new AdminMenu();
    @Override
    public void menu() {
        while (true) {
            int option =showHelper.brandMenuOption();
            switch (option){
                case 0:System.exit(0); break;
                case 1:
                    brandService.save();
                    break;
                case 2:
                    brandService.showAll();
                    break;
                case 3:
                    brandService.findById();
                    break;
                case 4:
                    brandService.findByName();
                    break;
                case 5:
                    brandService.update();
                    break;
                case 6:
                    brandService.delete();
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
