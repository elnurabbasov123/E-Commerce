package service.inter;

import model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    int save(Product product);
    List<Product> getAll();
    Optional<Product> getById(long id);
    Optional<Product> getByName(String name);
    int update(Product product);

}
