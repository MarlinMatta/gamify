package edu.uapa.web.app.gamify.repositories.school;

import edu.uapa.web.app.gamify.domains.schools.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {
    Page<Subject> findAllByNameLikeAndEnabled(Pageable pageable, String filterValue, boolean enabled);

    long countByNameLikeAndEnabled(String filterValue, boolean enabled);

    List<Subject> findAllByGrade_Id(long gradeId);

    List<Subject> findAllByTeacher_Id(long teacherId);
}
