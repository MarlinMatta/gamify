package edu.uapa.web.app.gamify.services.company;

import edu.uapa.web.app.gamify.domains.company.Position;
import edu.uapa.web.app.gamify.repositories.company.PositionRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PositionService {

    private final PositionRepo repository;

    public PositionService(PositionRepo repository) {
        this.repository = repository;
    }

    private Position merge(Position item, String userName) {
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

    Position bootStrap(Position item) {
        return merge(item, Constants.SYSTEM_USER);
    }
}