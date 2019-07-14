package edu.uapa.web.app.gamify.services.securities;

import edu.uapa.web.app.gamify.domains.securities.PermissionGroup;
import edu.uapa.web.app.gamify.repositories.securities.PermissionGroupRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PermissionGroupService {

    private final PermissionGroupRepo repository;

    public PermissionGroupService(PermissionGroupRepo repository) {
        this.repository = repository;
    }

    private PermissionGroup merge(PermissionGroup item, String userName) {
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

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(item -> {
            item.setEnabled(false);
            bootStrap(item);
        });
    }

    public PermissionGroup bootStrap(PermissionGroup item) {
        return merge(item, Constants.SYSTEM_USER);
    }

    public Optional<PermissionGroup> findByName(String name) {
        return repository.findByNameAndEnabled(name, true);
    }

    public Page<PermissionGroup> findAll(Pageable pageable, String filter) {
        return repository.findAllByNameLikeAndEnabled(pageable, filter, true);
    }

    public Long count(String filter) {
        return repository.countByNameLikeAndEnabled(filter, true);
    }
}