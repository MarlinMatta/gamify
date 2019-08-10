package edu.uapa.web.app.gamify.services.gamifies;

import edu.uapa.web.app.gamify.domains.gamifies.Problem;
import edu.uapa.web.app.gamify.repositories.gamifies.ProblemRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import edu.utesa.lib.models.enums.ExamDifficulty;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class ProblemService {

    private final ProblemRepo repository;
//    private final AnswerService answerService;

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
        return merge(item, Constants.SYSTEM_USER);
    }

    public Page<Problem> findAll(Pageable pageable, String filterValue) {
        return repository.findAllByQuestionLikeAndEnabled(pageable, filterValue, true);
    }

    public Page<Problem> findAll(Pageable pageable, ExamDifficulty difficulty) {
        return repository.findAllByExamDifficultyAndEnabled(pageable, difficulty, true);
    }

    public Page<Problem> findAllByExamDifficultyAndTeacher_IdAndTopic_IdAndEnabledAndCreatedDateBetween
            (Pageable pageable, ExamDifficulty difficulty, Long teacherId, Long topicId, Date from, Date to) {
        return repository.findAllByExamDifficultyAndTeacher_IdAndTopic_IdAndEnabledAndCreatedDateBetween(pageable, difficulty, teacherId, topicId, true, from, to);
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