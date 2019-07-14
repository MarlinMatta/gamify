package edu.uapa.web.app.gamify.services.securities;

import edu.uapa.web.app.gamify.domains.securities.Permission;
import edu.uapa.web.app.gamify.repositories.securities.PermissionRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    private final PermissionRepo repository;

    public PermissionService(PermissionRepo repository) {
        this.repository = repository;
    }

    private Permission merge(Permission item, String userName) {
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

    public Permission bootStrap(Permission item) {
        return merge(item, Constants.SYSTEM_USER);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(item -> {
            item.setEnabled(false);
            bootStrap(item);
        });
    }

    public Optional<Permission> findByCode(Integer code) {
        return repository.findByCodeAndEnabled(code, true);
    }

    public Page<Permission> findAll(Pageable pageable, String filter) {
        return repository.findAllByNameLikeAndEnabled(pageable, filter, true);
    }

    public Long count(String filter) {
        return repository.countByNameLikeAndEnabled(filter, true);
    }

    public List<Permission> findAllPermissions(Long userId, String filterValue) {
        return repository.findAllPermissions(userId, "'%" + filterValue + "%'");
    }
}