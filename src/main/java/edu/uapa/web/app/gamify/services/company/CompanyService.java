package edu.uapa.web.app.gamify.services.company;

import edu.uapa.web.app.gamify.domains.company.Company;
import edu.uapa.web.app.gamify.repositories.company.CompanyRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompanyService {

    private final CompanyRepo repository;

    public CompanyService(CompanyRepo repository) {
        this.repository = repository;
    }

    private Company merge(Company item, String userName) {
        if (item != null) {
            if (repository.existsById(item.getId())) {
                item.setLastModifiedDate(new Date());
                item.setLastModifiedBy(userName);
            } else {
                item.setCreatedBy(userName);
                item.setCreatedDate(new Date());
            }
            return repository.save(item);
        }
        return null;
    }

    Company bootStrap(Company item) {
        return merge(item, Constants.SYSTEM_USER);
    }
}