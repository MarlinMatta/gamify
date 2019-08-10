package edu.uapa.web.app.gamify.repositories.gamifies;

import edu.uapa.web.app.gamify.domains.gamifies.Problem;
import edu.utesa.lib.models.enums.ExamDifficulty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepo extends JpaRepository<Problem, Long> {
    Page<Problem> findAllByQuestionLikeAndEnabled(Pageable pageable, String filterValue, boolean enabled);

    Page<Problem> findAllByExamDifficultyAndEnabled(Pageable pageable, ExamDifficulty difficulty, boolean enabled);

    long countByQuestionLikeAndEnabled(String filterValue, boolean enabled);
}
