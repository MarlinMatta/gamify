package edu.uapa.web.app.gamify.services.school;

import edu.uapa.web.app.gamify.domains.schools.Subject;
import edu.uapa.web.app.gamify.repositories.school.SubjectRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepo repository;
    private final GradeService gradeService;

    private Subject merge(Subject item, String userName) {
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

    public Subject bootStrap(Subject item) {
        if (item.getGrade().getId() != null) {
            item.setGrade(gradeService.bootStrap(item.getGrade()));
        }
        return merge(item, Constants.SYSTEM_USER);
    }

    public Page<Subject> findAll(Pageable pageable, String filterValue) {
        return repository.findAllByNameLikeAndEnabled(pageable, filterValue, true);
    }

    public List<Subject> findAll() {
        return repository.findAll();
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