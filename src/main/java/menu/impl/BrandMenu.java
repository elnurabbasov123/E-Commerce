package menu.impl;
import menu.helper.MenuHelper;
import menu.inter.Menu;
import model.enums.Exceptions;
import model.exception.OperationNotFound;
import service.impl.IBrandService;
import service.inter.BrandService;
public class BrandMenu extends MenuHelper implements Menu {
    static BrandService brandService=new IBrandService();
    @Override
    public void menu() {
        while (true) {
            int option =brandMenuOption();
            switch (option){
                case 0:System.exit(0); break;
                case 1:
                    saveBrand();
                    break;
                case 2:
                   showAllBrand();
                    break;
                case 3:
                    findByIdBrand();
                    break;
                case 4:
                    findByNameBrand();
                    break;
                case 5:
                    updateBrand();
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
