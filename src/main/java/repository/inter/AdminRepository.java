package repository.inter;

import model.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    Optional<Admin> getById(long id);
    List<Admin> getAll();
    int add(Admin admin);
}
