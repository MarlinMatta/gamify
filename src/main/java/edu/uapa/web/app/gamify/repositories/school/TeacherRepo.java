package edu.uapa.web.app.gamify.repositories.school;

import edu.uapa.web.app.gamify.domains.schools.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    Page<Teacher> findAllByPersonFirstNameLikeAndEnabled(Pageable pageable, String filterValue, boolean enabled);

    long countByPersonFirstNameLikeAndEnabled(String filterValue, boolean enabled);
}
