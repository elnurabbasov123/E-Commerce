package service.inter;

import model.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    int save();
    List<Brand> getAll();
    Brand findById();
    Brand findByName();
    int update();
    int delete();
    void showAll();
}
