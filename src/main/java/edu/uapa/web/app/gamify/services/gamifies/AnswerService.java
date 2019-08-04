package edu.uapa.web.app.gamify.services.gamifies;

import edu.uapa.web.app.gamify.domains.gamifies.Answer;
import edu.uapa.web.app.gamify.repositories.gamifies.AnswerRepo;
import edu.uapa.web.app.gamify.services.school.SubjectService;
import edu.uapa.web.app.gamify.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepo repository;
    
    private Answer merge(Answer item, String userName) {
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

    public Answer bootStrap(Answer item) {
        return merge(item, Constants.SYSTEM_USER);
    }

    public Page<Answer> findAll(Pageable pageable, String filterValue) {
        return repository.findAllByDescriptionLikeAndEnabled(pageable, filterValue, true);
    }

    public List<Answer> findAll() {
        return repository.findAll();
    }

    public long count(String filterValue) {
        return repository.countByDescriptionLikeAndEnabled(filterValue, true);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(item -> {
            item.setEnabled(false);
            bootStrap(item);
        });
    }
}