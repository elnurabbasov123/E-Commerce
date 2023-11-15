package service.impl;

import model.entity.Brand;
import model.enums.Exceptions;
import model.exception.ObjectNotFound;
import repository.impl.IBrandRepository;
import repository.inter.BrandRepository;
import service.inter.BrandService;
import utility.InputUtil;

import java.util.List;
import java.util.Optional;

public class IBrandService implements BrandService {
    BrandRepository repository=new IBrandRepository();
    @Override
    public int save(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public List<Brand> getAll() {
        return repository.getAll();
    }

    @Override
    public Optional<Brand> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public int update(Brand brand) {
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
}
