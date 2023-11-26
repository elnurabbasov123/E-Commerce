package service.impl;
import lombok.RequiredArgsConstructor;
import model.entity.Category;
import model.enums.Exceptions;
import model.exception.ObjectNotFound;
import repository.impl.ICategoryRepository;
import repository.inter.CategoryRepository;
import service.inter.CategoryService;
import utility.InputUtil;
import java.util.List;
@RequiredArgsConstructor
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
    public Category findById() {
        long id = InputUtil.getInstance().inputInt("Id:");
        Category category1 = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Category", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(category1);

        return category1;
    }

    @Override
    public Category findByName() {
        String name = InputUtil.getInstance().inputString("Name:");
        Category category = repository.findByName(name)
                .orElseThrow(() -> new ObjectNotFound(name, "Category", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(category);

        return category;
    }

    @Override
    public int update() {
        List<Category> all = repository.getAll();
        all.stream().forEach(System.out::println);

        int id = InputUtil.getInstance().inputInt("Id:");
        Category category = all.stream().filter(category1 -> category1.getId()==id).findFirst()
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Category", Exceptions.OBJECT_NOT_FOUND));

        String name = InputUtil.getInstance().inputString("Enter new Name:");
        String description = InputUtil.getInstance().inputString("Enter new Description:");

        category.setName(name);
        category.setDescription(description);

        int update = repository.update(category);

        return update;
    }

    @Override
    public void delete() {
        List<Category> all = repository.getAll();
        all.stream().forEach(System.out::println);
        int id = InputUtil.getInstance().inputInt("Id:");
        Category category = all.stream().filter(ct -> ct.getId() == id).findFirst()
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Category", Exceptions.OBJECT_NOT_FOUND));
        category.setStatus(false);
        repository.update(category);
    }
}
