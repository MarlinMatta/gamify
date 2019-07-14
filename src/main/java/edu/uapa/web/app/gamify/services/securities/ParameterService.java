package edu.uapa.web.app.gamify.services.securities;

import edu.uapa.web.app.gamify.domains.securities.Parameter;
import edu.uapa.web.app.gamify.repositories.securities.ParameterRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ParameterService {

    private final ParameterRepo repository;

    public ParameterService(ParameterRepo repository) {
        this.repository = repository;
    }

    private Parameter merge(Parameter item, String userName) {
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

    public Parameter bootStrap(Parameter item) {
        return merge(item, Constants.SYSTEM_USER);
    }

    public Optional<Parameter> findByCode(Integer code) {
        return repository.findByCodeAndEnabled(code, true);
    }

    public Page<Parameter> findAll(Pageable pageable, String filterValue) {
        return repository.findAllByNameLikeAndEnabled(pageable, filterValue, true);
    }

    public long count(String filterValue) {
        return repository.countByNameLikeAndEnabled(filterValue, true);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(item -> {
            item.setEnabled(false);
            bootStrap(item);
        });
    }
}