package edu.uapa.web.app.gamify.services.company;

import edu.uapa.web.app.gamify.domains.company.Department;
import edu.uapa.web.app.gamify.repositories.company.DepartmentRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepartmentService {

    private final DepartmentRepo repository;

    public DepartmentService(DepartmentRepo repository) {
        this.repository = repository;
    }

    private Department merge(Department item, String userName) {
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

    Department bootStrap(Department item) {
        return merge(item, Constants.SYSTEM_USER);
    }
}