package repository.inter;

import model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    int save(Category category);
    List<Category> getAll();
    Optional<Category> findById(long id);
    Optional<Category> findByName(String name);
    int update(Category category);
}
