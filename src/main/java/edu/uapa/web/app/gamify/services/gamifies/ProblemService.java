package edu.uapa.web.app.gamify.services.gamifies;

import edu.uapa.web.app.gamify.domains.gamifies.Answer;
import edu.uapa.web.app.gamify.domains.gamifies.Problem;
import edu.uapa.web.app.gamify.repositories.gamifies.ProblemRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ProblemService {

    private final ProblemRepo repository;
    private final AnswerService answerService;

    private Problem merge(Problem item, String userName) {
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

    public Problem bootStrap(Problem item) {
        if (item.getCorrectAnswer().getId() != null) {
            item.setCorrectAnswer(answerService.bootStrap(item.getCorrectAnswer()));
        }
        return merge(item, Constants.SYSTEM_USER);
    }

    public Page<Problem> findAll(Pageable pageable, String filterValue) {
        return repository.findAllByQuestionLikeAndEnabled(pageable, filterValue, true);
    }

    public List<Problem> findAll() {
        return repository.findAll();
    }

    public long count(String filterValue) {
        return repository.countByQuestionLikeAndEnabled(filterValue, true);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(item -> {
            item.setEnabled(false);
            bootStrap(item);
        });
    }
}