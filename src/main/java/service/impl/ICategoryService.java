package service.impl;

import model.entity.Category;
import model.enums.Exceptions;
import model.exception.ObjectNotFound;
import repository.impl.ICategoryRepository;
import repository.inter.CategoryRepository;
import service.inter.CategoryService;
import utility.InputUtil;

import java.util.List;
import java.util.Optional;

public class ICategoryService implements CategoryService {
    static CategoryRepository repository = new ICategoryRepository();

    @Override
    public int save(Category category) {
        return repository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return repository.getAll();
    }

    @Override
    public Optional<Category> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public int update(Category category) {
        return repository.update(category);
    }
}
