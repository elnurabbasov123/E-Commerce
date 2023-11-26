package menu.helper;

import lombok.RequiredArgsConstructor;
import model.entity.*;
import model.enums.Exceptions;
import model.exception.ObjectNotFound;
import service.impl.IBrandService;
import service.impl.ICategoryService;
import service.impl.ICustomerService;
import service.inter.BrandService;
import service.inter.CategoryService;
import service.inter.CustomerService;
import utility.InputUtil;

import java.math.BigDecimal;
import java.util.List;

public class FillHelper {
    static BrandService brandService = new IBrandService();
    static CategoryService categoryService=new ICategoryService();
    //------------------ ADMIN ------------------
    public Admin fillAdmin() {
        String name = utility.InputUtil.getInstance().inputString("Name:");
        String login = utility.InputUtil.getInstance().inputString("Login:");
        String password = utility.InputUtil.getInstance().inputString("Password:");

        return new Admin(name, login, password);
    }
    //------------------ BRAND ------------------
    public Brand fillBrand() {
        String name = InputUtil.getInstance().inputString("Name:");
        String description = InputUtil.getInstance().inputString("Description:");

        return new Brand(name, description);
    }
    //------------------ CATEGORY ------------------
    public Category fillCategory() {
        String name = InputUtil.getInstance().inputString("Name:");
        String description = InputUtil.getInstance().inputString("Description:");

        return Category.builder().status(true).description(description).name(name).build();
    }
    //---------------------------PRODUCT---------------------------
    public Product fillProduct() {
        String name = InputUtil.getInstance().inputString("Name:");
        double doubleAmount = InputUtil.getInstance().inputDouble("Amount:");
        int available = InputUtil.getInstance().inputInt("How many are available:");
        BigDecimal amount = new BigDecimal(doubleAmount);

        List<Brand> brands = brandService.getAll();
        brands.stream().forEach(System.out::println);
        int brandId = InputUtil.getInstance().inputInt("Brand Id:");

        List<Category> categories = categoryService.getAll();
        categories.stream().forEach(System.out::println);
        int categoryId = InputUtil.getInstance().inputInt("Category Id:");

        Brand brand = brands.stream()
                .filter(br -> br.getId() == brandId).findFirst()
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(brandId), "Brand", Exceptions.OBJECT_NOT_FOUND));

        Category category = categories.stream().filter(ct -> ct.getId() == categoryId)
                .findFirst().orElseThrow(() -> new ObjectNotFound(String.valueOf(categoryId), "Category", Exceptions.OBJECT_NOT_FOUND));

        System.out.println("---- Product Details ----");

        String descrIption = InputUtil.getInstance().inputString("DescrIption:");
        String color = InputUtil.getInstance().inputString("Color:");
        double weight = InputUtil.getInstance().inputDouble("Weight:");
        ProductDetails productDetails = new ProductDetails(descrIption, color, weight);

        return Product.builder().remainCount(available).status(true).name(name).amount(amount)
                .brand(brand).productDetails(productDetails).category(category).build();

    }
    public Customer fillCustomer() {
        String name = InputUtil.getInstance().inputString("Name:");
        String surname = InputUtil.getInstance().inputString("Surname:");
        String fin = InputUtil.getInstance().inputString("Fin:");
        String login = InputUtil.getInstance().inputString("Login:");
        String password = InputUtil.getInstance().inputString("Password:");
        String adress = InputUtil.getInstance().inputString("Adress:");
        String birthDate = InputUtil.getInstance().inputString("BirthDate(ex 21/02/1999):");
        Double v = InputUtil.getInstance().inputDouble("Amount:");
        BigDecimal amount = new BigDecimal(v);

        return new Customer(name, surname, birthDate, fin, amount, login, password, adress);
    }
}
