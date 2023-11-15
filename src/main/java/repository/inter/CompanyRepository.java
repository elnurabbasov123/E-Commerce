package repository.inter;

import model.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {
    int update(Company company);
    Optional<Company> getById(long id);
}
