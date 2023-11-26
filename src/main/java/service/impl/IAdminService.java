package service.impl;

import lombok.RequiredArgsConstructor;
import model.config.Config;
import model.entity.Admin;
import model.enums.Exceptions;
import model.exception.PasswordOrLoginNotFound;
import repository.impl.IAdminRepository;
import repository.inter.AdminRepository;
import service.inter.AdminService;
import utility.InputUtil;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class IAdminService extends Config implements AdminService {
    static AdminRepository adminRepository = new IAdminRepository();

    @Override
    public Admin isAdmin() {
        List<Admin> admins = getAll();
        String login = InputUtil.getInstance().inputString("Login:");
        String password = InputUtil.getInstance().inputString("Password:");

        Admin admin1 = admins.stream()
                .filter(admin -> admin.getLogin().equals(login))
                .filter(admin -> admin.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new PasswordOrLoginNotFound(Exceptions.PASSWORD_OR_LOGIN_NOT_FOUND, "Admin"));

        return admin1;
    }

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
