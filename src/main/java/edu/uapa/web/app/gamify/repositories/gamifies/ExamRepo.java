package edu.uapa.web.app.gamify.repositories.gamifies;

import edu.uapa.web.app.gamify.domains.gamifies.Exam;
import edu.utesa.lib.models.enums.GameDifficulty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Long> {
    Page<Exam> findAllBySubject_NameLikeAndEnabled(Pageable pageable, String filterValue, boolean enabled);

    Exam findByIdAndEnabled(Long id, boolean enabled);

    long countBySubject_NameLikeAndEnabled(String filterValue, boolean enabled);

    List<Exam> findAllByGameDifficultyAndSubject_IdAndTopic_IdAndCreatedDateBetween(GameDifficulty difficulty, long subjectId, long topicId, Date from, Date to);

    List<Exam> findAllByGameDifficultyAndSubject_IdAndTopic_IdAndEnabled(GameDifficulty difficulty, Long subjectId, Long topicId, boolean enabled);

    List<Exam> findAllBySubject_IdAndTopic_IdAndEnabled(Long subjectId, Long topicId, boolean enabled);
}
