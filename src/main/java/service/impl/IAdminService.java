package service.impl;

import model.config.Config;
import model.entity.Admin;
import repository.impl.IAdminRepository;
import repository.inter.AdminRepository;
import service.inter.AdminService;

import java.util.List;
import java.util.Optional;

public class IAdminService extends Config implements AdminService {
    AdminRepository adminRepository=new IAdminRepository();
    @Override
    public Optional<Admin> getById(long id) {
        return adminRepository.getById(id);
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.getAll();
    }

    @Override
    public int add(Admin admin) {
        return adminRepository.add(admin);
    }
}
