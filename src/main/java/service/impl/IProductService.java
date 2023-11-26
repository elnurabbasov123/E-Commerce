package service.impl;
import lombok.RequiredArgsConstructor;
import menu.helper.FillHelper;
import menu.helper.ShowHelper;
import model.entity.*;
import model.enums.Exceptions;
import model.exception.InsufficientFundsException;
import model.exception.ObjectNotFound;
import model.exception.OperationNotFound;
import repository.impl.IProductRepository;
import repository.inter.ProductRepository;
import service.inter.*;
import utility.InputUtil;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class IProductService implements ProductService {
     static ProductRepository productRepository = new IProductRepository();
     static CustomerService customerService = new ICustomerService();
     static CartService cartService = new ICartService();
     static CompanyService companyService = new IComppanyService();
     static OrderService orderService = new IOrderService();
     static ShowHelper showHelper = new ShowHelper();
     static FillHelper fillHelper = new FillHelper();
    @Override
    public int save() {
        Product fill = fillHelper.fillProduct();
        int save = productRepository.save(fill);

        return save;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById() {
        int id = InputUtil.getInstance().inputInt("Id:");
        Product product = productRepository.getById(id)
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Product", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(product);

        return product;
    }

    @Override
    public Product getByName() {
        String name = InputUtil.getInstance().inputString("Name:");
        Product product = productRepository.getByName(name)
                .orElseThrow(() -> new ObjectNotFound(name, "Product", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(product);

        return product;
    }

    @Override
    public int update() {
        List<Product> all = productRepository.getAll();
        all.stream().forEach(System.out::println);
        int id = InputUtil.getInstance().inputInt("Id Product :");
        Product product = all.stream().filter(product1 -> product1.getId() == id).findFirst()
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Product", Exceptions.OBJECT_NOT_FOUND));

        Product newProduct = fillHelper.fillProduct();

        product.setAmount(newProduct.getAmount());
        product.setCategory(newProduct.getCategory());
        product.setBrand(newProduct.getBrand());
        product.setName(newProduct.getName());
        product.setProductDetails(newProduct.getProductDetails());
        product.setRemainCount(newProduct.getRemainCount());

        int update = productRepository.update(product);

        return update;
    }

    @Override
    public void showAll() {
        productRepository.getAll().stream().forEach(System.out::println);
    }

    @Override
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
            productRepository.update(products.get(i));
        }
    }

    @Override
    public void delete() {
        List<Product> all = productRepository.getAll();
        all.stream().forEach(System.out::println);
        int id = InputUtil.getInstance().inputInt("Id:");
        Product product = all.stream().filter(pr -> pr.getId() == id).findFirst()
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Product", Exceptions.OBJECT_NOT_FOUND));
        product.setStatus(false);

        productRepository.update(product);
    }

    @Override
    public void addToCart(Customer customer) {
        if (customer.getCart() == null) {
            List<Product> all = productRepository.getAll();
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
            List<Product> all = productRepository.getAll();
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

    @Override
    public void deleteFromCart(Customer customer) {
        if (customer.getCart() != null) {
            Cart cart = customer.getCart();
            cart.getProducts().stream().forEach(System.out::println);
            int id = InputUtil.getInstance().inputInt("Which id Product Do You want to delete ?:");
            Product product = productRepository.getById(id)
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

    @Override
    public void buy(Customer customer) {
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

    @Override
    public int setStar() {
        int option = InputUtil.getInstance().inputInt("[0] - > Exit \n" +
                "[1] - > In star terms\n" +
                "[2] - > Skip\n" +
                "Select:");
        int star = 0;
        switch (option) {
            case 1:
                int stars = showHelper.starTerms();
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

    @Override
    public String setComment() {
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
}

