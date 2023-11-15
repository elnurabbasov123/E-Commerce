package repository.impl;

import model.config.Config;
import model.entity.Company;
import model.enums.Exceptions;
import model.exception.ObjectDontUpdated;
import repository.inter.CompanyRepository;

import java.util.Optional;

public class ICompanyRepository extends Config implements CompanyRepository {
    @Override
    public int update(Company company) {
        try {
            getEntityTransaction().begin();
            getEntityManager().merge(company);
            getEntityTransaction().commit();
            return 1;
        }catch (Exception ex){
            getEntityTransaction().rollback();
            throw new ObjectDontUpdated(Exceptions.DONT_UPDATED,"Company");
        }
    }

    @Override
    public Optional<Company> getById(long id) {
        Company singleResult = getEntityManager().createQuery("select c from company c where c.id=:id and c.status =true", Company.class)
                .setParameter("id",id).getSingleResult();
      return   Optional.ofNullable(singleResult);
    }
}
