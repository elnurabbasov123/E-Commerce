package menu.helper;

import menu.impl.AdminMenu;
import menu.impl.CustomerMenu;
import model.entity.*;
import model.enums.Exceptions;
import model.exception.*;
import service.impl.*;
import service.inter.*;
import utility.InputUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
   static AdminService adminService = new IAdminService();
   static BrandService brandService = new IBrandService();
   static CategoryService categoryService = new ICategoryService();
   static ProductService productService = new IProductService();
   static CustomerService customerService = new ICustomerService();
   static CartService cartService = new ICartService();
   static CompanyService companyService = new IComppanyService();
   static OrderService orderService = new IOrderService();
   static protected AdminMenu adminMenu = new AdminMenu();
   static protected CustomerMenu customerMenu = new CustomerMenu();

    //------------------ ADMIN ------------------
    public Admin fillAdmin() {
        String name = utility.InputUtil.getInstance().inputString("Name:");
        String login = utility.InputUtil.getInstance().inputString("Login:");
        String password = utility.InputUtil.getInstance().inputString("Password:");

        return new Admin(name, login, password);
    }


    public Admin isAdmin() {
        List<Admin> admins = adminService.getAll();
        String login = InputUtil.getInstance().inputString("Login:");
        String password = InputUtil.getInstance().inputString("Password:");

        Admin admin1 = admins.stream()
                .filter(admin -> admin.getLogin().equals(login))
                .filter(admin -> admin.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new PasswordOrLoginNotFound(Exceptions.PASSWORD_OR_LOGIN_NOT_FOUND, "Admin"));

        return admin1;
    }

    public int adminMenuOption() {
        return InputUtil.getInstance().inputInt("\n[0]->Exit" +
                "\n[1]->Category" +
                "\n[2]->Brand" +
                "\n[3]->Product" +
                "\n[4]->Register" +
                "\n[5]->Back" +
                "\nSelect:");
    }

    //------------------ BRAND ------------------
    public Brand fillBrand() {
        String name = InputUtil.getInstance().inputString("Name:");
        String description = InputUtil.getInstance().inputString("Description:");

        return new Brand(name, description);
    }

    public void updateBrand() {
        brandService.getAll().stream().forEach(System.out::println);
        int id = InputUtil.getInstance().inputInt("Id:");

        Brand brand = brandService.findById(id)
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Brand", Exceptions.OBJECT_NOT_FOUND));

        String name = InputUtil.getInstance().inputString("Enter new Name:");
        String description = InputUtil.getInstance().inputString("Enter new Description:");
        brand.setName(name);
        brand.setDescription(description);

        brandService.update(brand);
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

    public void findByIdBrand() {
        int id = InputUtil.getInstance().inputInt("Id:");
        Brand brand1 = brandService.findById(id)
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Brand", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(brand1);
    }

    public void findByNameBrand() {
        String name = InputUtil.getInstance().inputString("Name:");
        Brand brand2 = brandService.findByName(name)
                .orElseThrow(() -> new ObjectNotFound(name, "Brand", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(brand2);
    }

    public void showAllBrand() {
        brandService.getAll().stream().forEach(System.out::println);
    }

    public void saveBrand() {
        Brand brand = fillBrand();
        brandService.save(brand);
    }

    //------------------ CATEGORY ------------------
    public Category fillCategory() {
        String name = InputUtil.getInstance().inputString("Name:");
        String description = InputUtil.getInstance().inputString("Description:");

        return Category.builder().status(true).description(description).name(name).build();
    }

    public void updateCategory() {
        categoryService.getAll().stream().forEach(System.out::println);
        int id = InputUtil.getInstance().inputInt("Id:");
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Category", Exceptions.OBJECT_NOT_FOUND));

        String name = InputUtil.getInstance().inputString("Enter new Name:");
        String description = InputUtil.getInstance().inputString("Enter new Description:");

        category.setName(name);
        category.setDescription(description);

        categoryService.update(category);
    }

    public void findByNameCategory() {
        String name = InputUtil.getInstance().inputString("Name:");
        Category category1 = categoryService.findByName(name)
                .orElseThrow(() -> new ObjectNotFound(name, "Category", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(category1);
    }

    public void findByIdCategory() {
        long id = InputUtil.getInstance().inputInt("Id:");
        Category category1 = categoryService.findById(id)
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Category", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(category1);
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

    public void deleteCategory() {
        List<Category> all = categoryService.getAll();
        all.stream().forEach(System.out::println);
        int id = InputUtil.getInstance().inputInt("Id:");
        Category category = all.stream().filter(ct -> ct.getId() == id).findFirst()
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Category", Exceptions.OBJECT_NOT_FOUND));
        category.setStatus(false);
        categoryService.update(category);
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

    public void updateProduct() {
        productService.getAll().stream().forEach(System.out::println);
        int id = InputUtil.getInstance().inputInt("Id Product :");
        Product product = productService.getById(id)
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Product", Exceptions.OBJECT_NOT_FOUND));


        String name = InputUtil.getInstance().inputString("Enter new Name:");
        double doubleAmount = InputUtil.getInstance().inputDouble("Enter new Amount:");
        int availablecount = InputUtil.getInstance().inputInt("Enter new available count:");
        BigDecimal amount = new BigDecimal(doubleAmount);

        List<Brand> brands = brandService.getAll();
        brands.stream().forEach(System.out::println);
        int brandId = InputUtil.getInstance().inputInt("Enter new Brand Id:");

        List<Category> categories = categoryService.getAll();
        categories.stream().forEach(System.out::println);
        int categoryId = InputUtil.getInstance().inputInt("Enter new Category Id:");

        Brand brand = brands.stream()
                .filter(br -> br.getId() == brandId).findFirst()
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(brandId), "Brand", Exceptions.OBJECT_NOT_FOUND));

        Category category = categories.stream().filter(ct -> ct.getId() == categoryId)
                .findFirst().orElseThrow(() -> new ObjectNotFound(String.valueOf(categoryId), "Category", Exceptions.OBJECT_NOT_FOUND));

        System.out.println("---- Product Details ----");

        String descrIption = InputUtil.getInstance().inputString("Enter new DescrIption:");
        String color = InputUtil.getInstance().inputString("Enter new Color:");
        double weight = InputUtil.getInstance().inputDouble("Enter new Weight:");
        ProductDetails productDetails = new ProductDetails(descrIption, color, weight);

        product.setAmount(amount);
        product.setCategory(category);
        product.setBrand(brand);
        product.setName(name);
        product.setProductDetails(productDetails);
        product.setRemainCount(availablecount);

        productService.update(product);
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

    public void saveProduct() {
        Product fill = fillProduct();
        productService.save(fill);
    }

    public void showAllProduct() {
        productService.getAll().stream().forEach(System.out::println);
    }

    public void findByIdProduct() {
        int id = InputUtil.getInstance().inputInt("Id:");
        Product product = productService.getById(id)
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Product", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(product);
    }

    public void findByNameProduct() {
        String name = InputUtil.getInstance().inputString("Name:");
        Product product = productService.getByName(name)
                .orElseThrow(() -> new ObjectNotFound(name, "Product", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(product);
    }

    public void addCommentAndStar(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            products.get(i).setRemainCount(products.get(i).getRemainCount() - 1);
            products.get(i).setSales(products.get(i).getSales() + 1);
            System.out.println(products.get(i));
            String comment = setComment();
            if (comment != null) {
                products.get(i).getComments().add(comment);
            }
            int stars = setStar();
            if (stars > 0) {
                products.get(i).totalStar(stars);
                products.get(i).setStar();
            }
            productService.update(products.get(i));
        }
    }

    public int setStar() {
        int option = InputUtil.getInstance().inputInt("[0] - > Exit \n" +
                "[1] - > In star terms\n" +
                "[2] - > Skip\n" +
                "Select:");
        int star = 0;
        switch (option) {
            case 1:
                int stars = starTerms();
                if (stars > 0 && stars < 6) {
                    star = stars;
                } else {
                    throw new OperationNotFound(Exceptions.OPERATION_NOT_FOUND);
                }
                break;
            case 2:
                break;
            default:
                throw new OperationNotFound(Exceptions.OPERATION_NOT_FOUND);
        }
        return star;
    }

    public int starTerms() {
        return InputUtil.getInstance().inputInt("[1] -> *\n" +
                "[2] -> **\n" +
                "[3] -> ***\n" +
                "[4] -> ****\n" +
                "[5] -> *****\n" +
                "Select:");
    }

    private String setComment() {
        int s = InputUtil.getInstance().inputInt("[0] - > Exit \n" +
                "[1] - > Comment product\n" +
                "[2] - > Skip\n" +
                "Select:");
        String comment = null;
        switch (s) {
            case 1:
                comment = InputUtil.getInstance().inputString("Your comment:");
                break;
            case 2:
                break;
            default:
                throw new OperationNotFound(Exceptions.OPERATION_NOT_FOUND);
        }
        return comment;
    }

    public void deleteProduct() {
        List<Product> all = productService.getAll();
        all.stream().forEach(System.out::println);
        int id = InputUtil.getInstance().inputInt("Id:");
        Product product = all.stream().filter(pr -> pr.getId() == id).findFirst()
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Product", Exceptions.OBJECT_NOT_FOUND));
        product.setStatus(false);
        productService.update(product);
    }


    //--------------------CUSTOMER-----------------------------

    public Customer isCustomer() {
        String login = InputUtil.getInstance().inputString("Login:");
        String password = InputUtil.getInstance().inputString("Password:");

        return customerService.checkCustomer(login, password);
    }

    public Customer registerCustomer() {
        Customer customer = fillCustomer();
        int added = customerService.add(customer);
        if (added == 1) {
            return customer;
        } else {
            throw new ObjectDontAddException(Exceptions.OBJECT_DONT_ADD, "Customer");
        }
    }

    private Customer fillCustomer() {
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

    public Customer loginCustomer() {
        int i = InputUtil.getInstance().inputInt("[0]->Exit\n" +
                "[1]->Login\n" +
                "[2]->Sign Up\n" +
                "Select:");
        Customer customer = null;
        switch (i) {
            case 0:
                System.exit(0);
                break;
            case 1:
                customer = isCustomer();
                break;
            case 2:
                customer = registerCustomer();
                break;
            default:
                throw new OperationNotFound(Exceptions.OPERATION_NOT_FOUND);
        }
        return customer;
    }

    public void addProductToCart(Customer customer) {
        if (customer.getCart() == null) {
            List<Product> all = productService.getAll();
            all.stream().forEach(System.out::println);

            int idProduct = InputUtil.getInstance().inputInt("Which id product do you want add to cart ?:");
            Product product = all.stream().filter(pr -> pr.getId() == idProduct).findFirst()
                    .orElseThrow(() -> new ObjectNotFound(String.valueOf(idProduct), "Product", Exceptions.OBJECT_NOT_FOUND));
            List<Product> products = new ArrayList<>();
            products.add(product);
            Cart cart = new Cart(products);
            customer.setCart(cart);

            cartService.save(cart);
            customerService.update(customer);
        } else {
            Cart cart = customer.getCart();
            List<Product> all = productService.getAll();
            all.stream().forEach(System.out::println);

            int idProduct = InputUtil.getInstance().inputInt("Which id product do you want add to cart ?:");
            Product product = all.stream().filter(pr -> pr.getId() == idProduct).findFirst()
                    .orElseThrow(() -> new ObjectNotFound(String.valueOf(idProduct), "Product", Exceptions.OBJECT_NOT_FOUND));
            List<Product> products = cart.getProducts();
            products.add(product);
            cart.setProducts(products);

            customer.setCart(cart);

            cartService.update(cart);
            customerService.update(customer);
        }
    }

    public void deleteProductToCart(Customer customer) {
        if (customer.getCart() != null) {
            Cart cart = customer.getCart();
            cart.getProducts().stream().forEach(System.out::println);
            int id = InputUtil.getInstance().inputInt("Which id Product Do You want to delete ?:");
            Product product = productService.getById(id)
                    .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Product", Exceptions.OBJECT_NOT_FOUND));
            List<Product> products = cart.getProducts();
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == product.getId()) {
                    products.remove(i);
                }
            }
            cart.setProducts(products);
            cartService.update(cart);
            customer.setCart(cart);
            customerService.update(customer);
        } else {
            throw new ObjectNotFound(String.valueOf(customer.getCart().getId()), "Cart", Exceptions.OBJECT_NOT_FOUND);
        }
    }

    public void buyProduct(Customer customer) {
        if (customer.getCart().getProducts().size() < 1) {
            System.out.println("Dear " + customer.getName() + " " + customer.getSurname() + " Your Cart is Empty please add products to cart ");
            return;
        }
        Company company = companyService.getById(1)
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(1), "id Company", Exceptions.OBJECT_NOT_FOUND));
        Cart cart = customer.getCart();
        List<Product> products = cart.getProducts();
        products.forEach(System.out::println);

        int operation = InputUtil.getInstance().inputInt("\n" +
                "Amount your purchase " + cart.getTotalAmount() + "$,Are you sure you want to make a purchase ?:\n" +
                "[1]->Yes\n" +
                "[2]->No\n" +
                "Select:");
        if (operation == 1) {
            if (customer.getAccount().doubleValue() > cart.getTotalAmount().doubleValue()
                    || customer.getAccount().doubleValue() == cart.getTotalAmount().doubleValue()) {

                String location = InputUtil.getInstance().inputString("Location:");
                String phone = InputUtil.getInstance().inputString("Phone number:");

                customer.reduceAmount(cart.getTotalAmount());
                company.increaseAmount(cart.getTotalAmount());

                Order order = new Order(location, phone, customer.getName(), products, cart.getTotalAmount());
                cart.setProducts(new ArrayList<>());

                orderService.buyProduct(order, cart, company, customer, products);
                System.out.println("Your Order Successfully Completed !");
                System.out.println(order);
            } else {
                throw new InsufficientFundsException(Exceptions.INSUFFICIENT_FUNDS, customer.getName(), customer.getSurname());
            }
        } else if (operation == 2) {
            return;
        } else {
            throw new OperationNotFound(Exceptions.OPERATION_NOT_FOUND);
        }
    }


    public void increaseBalance(Customer customer) {
        Double v = InputUtil.getInstance().inputDouble("Amount:");
        customer.increaseAmount(new BigDecimal(v));

        customerService.update(customer);
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
