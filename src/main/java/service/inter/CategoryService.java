package service.inter;

import model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    int save(Category category);
    List<Category> getAll();
    Category findById();
    Category findByName();
    int update();
    void delete();
}
