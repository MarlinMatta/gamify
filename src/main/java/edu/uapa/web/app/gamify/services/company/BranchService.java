package edu.uapa.web.app.gamify.services.company;

import edu.uapa.web.app.gamify.domains.company.Branch;
import edu.uapa.web.app.gamify.repositories.company.BranchRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BranchService {

    private final BranchRepo repository;

    public BranchService(BranchRepo repository) {
        this.repository = repository;
    }

    private Branch merge(Branch item, String userName) {
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

    Branch bootStrap(Branch item) {
        return merge(item, Constants.SYSTEM_USER);
    }
}