package edu.uapa.web.app.gamify.services.school;

import edu.uapa.web.app.gamify.domains.schools.Grade;
import edu.uapa.web.app.gamify.repositories.school.GradeRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GradeService {

    private final GradeRepo repository;

    public GradeService(GradeRepo repository) {
        this.repository = repository;
    }

    private Grade merge(Grade item, String userName) {
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

    public Grade bootStrap(Grade item) {
        return merge(item, Constants.SYSTEM_USER);
    }

    public Page<Grade> findAll(Pageable pageable, String filterValue) {
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