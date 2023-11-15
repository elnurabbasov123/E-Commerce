package repository.inter;

import model.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {
    int save(Brand brand);
    List<Brand> getAll();
    Optional<Brand> findById(long id);
    Optional<Brand> findByName(String name);
    int update(Brand brand);
}
