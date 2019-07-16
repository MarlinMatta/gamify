package edu.uapa.web.app.gamify.services.company;

import edu.uapa.web.app.gamify.domains.company.Employee;
import edu.uapa.web.app.gamify.repositories.company.EmployeeRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeService {

    private final EmployeeRepo repository;

    public EmployeeService(EmployeeRepo repository) {
        this.repository = repository;
    }

    private Employee merge(Employee item, String userName) {
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

    Employee bootStrap(Employee item) {
        return merge(item, Constants.SYSTEM_USER);
    }
}