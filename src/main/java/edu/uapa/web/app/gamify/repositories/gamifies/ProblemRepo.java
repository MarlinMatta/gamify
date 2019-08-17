package edu.uapa.web.app.gamify.repositories.gamifies;

import edu.uapa.web.app.gamify.domains.gamifies.Problem;
import edu.utesa.lib.models.enums.GameDifficulty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ProblemRepo extends JpaRepository<Problem, Long> {
    Page<Problem> findAllByQuestionLikeAndEnabled(Pageable pageable, String filterValue, boolean enabled);

    Page<Problem> findAllByGameDifficultyAndTopic_IdAndEnabled(Pageable pageable, GameDifficulty difficulty, Long topicId, boolean enabled);

    Page<Problem> findAllByGameDifficultyAndTeacher_IdAndTopic_IdAndEnabledAndCreatedDateBetween(Pageable pageable, GameDifficulty difficulty, Long teacherId, Long topicId, boolean enabled, Date from, Date to);

    long countByQuestionLikeAndEnabled(String filterValue, boolean enabled);
}
