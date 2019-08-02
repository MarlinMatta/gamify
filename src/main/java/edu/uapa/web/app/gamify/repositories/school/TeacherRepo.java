package edu.uapa.web.app.gamify.repositories.school;

import edu.uapa.web.app.gamify.domains.schools.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    Page<Teacher> findAllByPerson_NamesLikeAndEnabled(Pageable pageable, String filterValue, boolean enabled);

    long countByPerson_NamesLikeAndEnabled(String filterValue, boolean enabled);
}
