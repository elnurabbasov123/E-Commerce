package service.impl;

import lombok.RequiredArgsConstructor;
import model.entity.Company;
import repository.impl.ICompanyRepository;
import repository.inter.CompanyRepository;
import service.inter.CompanyService;
import java.util.Optional;
@RequiredArgsConstructor
public class IComppanyService implements CompanyService {
    static CompanyRepository repo = new ICompanyRepository();
    @Override
    public int update(Company company) {
        return repo.update(company);
    }

    @Override
    public Optional<Company> getById(long id) {
        return repo.getById(id);
    }
}
