package service.impl;
import model.entity.Product;
import model.enums.Exceptions;
import model.exception.ObjectNotFound;
import repository.impl.IProductRepository;
import repository.inter.ProductRepository;
import service.inter.ProductService;
import utility.InputUtil;
import java.util.List;
import java.util.Optional;

public class IProductService implements ProductService {
    static ProductRepository productRepository = new IProductRepository();

    @Override
    public int save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Optional<Product> getById(long id) {
        return productRepository.getById(id);
    }

    @Override
    public Optional<Product> getByName(String name) {
        return productRepository.getByName(name);
    }

    @Override
    public int update(Product product) {
        return productRepository.update(product);
    }

}

