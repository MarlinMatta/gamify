package edu.uapa.web.app.gamify.repositories.gamifies;

import edu.uapa.web.app.gamify.domains.gamifies.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {
    Page<Answer> findAllByDescriptionLikeAndEnabled(Pageable pageable, String filterValue, boolean enabled);

    long countByDescriptionLikeAndEnabled(String filterValue, boolean enabled);
}
