package service.impl;

import lombok.RequiredArgsConstructor;
import model.helper.FillHelper;
import model.entity.Brand;
import model.enums.Exceptions;
import model.exception.ObjectNotFound;
import repository.impl.IBrandRepository;
import repository.inter.BrandRepository;
import service.inter.BrandService;
import utility.InputUtil;
import java.util.List;
@RequiredArgsConstructor
public class IBrandService implements BrandService {
    static BrandRepository repository = new IBrandRepository();
    static FillHelper fillHelper = new FillHelper();
    @Override
    public int save() {
        Brand brand = fillHelper.fillBrand();
        int saveD = repository.save(brand);

        return saveD;
    }

    @Override
    public List<Brand> getAll() {
        return repository.getAll();
    }

    @Override
    public Brand findById() {
        int id = InputUtil.getInstance().inputInt("Id:");
        Brand brand = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Brand", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(brand);

        return brand;
    }

    @Override
    public Brand findByName() {
        String name = InputUtil.getInstance().inputString("Name:");
        Brand brand = repository.findByName(name)
                .orElseThrow(() -> new ObjectNotFound(name, "Brand", Exceptions.OBJECT_NOT_FOUND));
        System.out.println(brand);

        return brand;
    }

    @Override
    public int update() {
        getAll().stream().forEach(System.out::println);
        int id = InputUtil.getInstance().inputInt("Id:");

        Brand brand = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Brand", Exceptions.OBJECT_NOT_FOUND));

        String name = InputUtil.getInstance().inputString("Enter new Name:");
        String description = InputUtil.getInstance().inputString("Enter new Description:");
        brand.setName(name);
        brand.setDescription(description);

        return repository.update(brand);
    }

    @Override
    public int delete() {
        List<Brand> all = repository.getAll();
        all.stream().forEach(System.out::println);
        int id = InputUtil.getInstance().inputInt("Id:");
        Brand brand = all.stream().filter(br -> br.getId() == id).findFirst()
                .orElseThrow(() -> new ObjectNotFound(String.valueOf(id), "Brand", Exceptions.OBJECT_NOT_FOUND));
        brand.setStatus(false);
        return repository.update(brand);
    }

    @Override
    public void showAll() {
        repository.getAll().stream().forEach(System.out::println);
    }
}
