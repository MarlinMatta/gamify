package edu.uapa.web.app.gamify.repositories.gamifies;

import edu.uapa.web.app.gamify.domains.gamifies.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Long> {
    Page<Exam> findAllBySubject_NameLikeAndEnabled(Pageable pageable, String filterValue, boolean enabled);

    long countBySubject_NameLikeAndEnabled(String filterValue, boolean enabled);
}
