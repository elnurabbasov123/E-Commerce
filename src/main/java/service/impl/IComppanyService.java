package service.impl;

import model.entity.Company;
import repository.impl.ICompanyRepository;
import repository.inter.CompanyRepository;
import service.inter.CompanyService;

import java.util.List;
import java.util.Optional;

public class IComppanyService implements CompanyService {
    CompanyRepository repo=new ICompanyRepository();
    @Override
    public int update(Company company) {
        return repo.update(company);
    }

    @Override
    public Optional<Company> getById(long id) {
        return repo.getById(id);
    }
}
