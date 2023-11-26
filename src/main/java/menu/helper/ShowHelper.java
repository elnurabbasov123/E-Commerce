package menu.helper;

import utility.InputUtil;

public class ShowHelper {
    public int adminMenuOption() {
        return InputUtil.getInstance().inputInt("\n[0]->Exit" +
                "\n[1]->Category" +
                "\n[2]->Brand" +
                "\n[3]->Product" +
                "\n[4]->Register" +
                "\n[5]->Back" +
                "\nSelect:");
    }

    public int brandMenuOption() {
        return InputUtil.getInstance().inputInt("[0] - > Exit \n" +
                "[1] - > Save brand \n" +
                "[2] - > Show all brand\n" +
                "[3] - > Find by id \n" +
                "[4] - > Find by name\n" +
                "[5] - > Update brand \n" +
                "[6] ->  Delete brand\n" +
                "[7] ->  Back\n" +
                "Select:");
    }
    public int categoryMenuOption() {
        return InputUtil.getInstance().inputInt("\n[0]->Exit" +
                "\n[1]->Save category" +
                "\n[2]->Show All category" +
                "\n[3]->Find by id" +
                "\n[4]->Find by name" +
                "\n[5]->Update category" +
                "\n[6]->Delete category" +
                "\n[7]->Back" +
                "\nSelect:");
    }
    public int productMenuOption() {
        return InputUtil.getInstance().inputInt("[0] - > Exit \n" +
                "[1] - > Save product \n" +
                "[2] - > Show all product\n" +
                "[3] - > Find by id \n" +
                "[4] - > Find by name\n" +
                "[5] - > Update product \n" +
                "[6] - > Delete product\n" +
                "[7] - > Back\n" +
                "Select:");
    }
    public int starTerms() {
        return InputUtil.getInstance().inputInt("[1] -> *\n" +
                "[2] -> **\n" +
                "[3] -> ***\n" +
                "[4] -> ****\n" +
                "[5] -> *****\n" +
                "Select:");
    }
    public int customerMenuOption() {
        return InputUtil.getInstance().inputInt("[0] - > Exit \n" +
                "[1] - > Show all product \n" +
                "[2] - > Add product to cart\n" +
                "[3] - > Delete product to cart\n" +
                "[4] - > Buy product in cart\n" +
                "[5] - > Increase balance \n" +
                "[6] - > Back\n" +
                "Select:");
    }


    //------------------------MAIN MENU -----------------

    public int mainMenuOption(){
        return InputUtil.getInstance().inputInt("\n[0]->Exit" +
                "\n[1]->Admin" +
                "\n[2]->Customer" +
                "\nSelect:");
    }
}
