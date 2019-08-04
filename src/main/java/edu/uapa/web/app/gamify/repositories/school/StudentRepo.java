package edu.uapa.web.app.gamify.repositories.school;

import edu.uapa.web.app.gamify.domains.schools.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    Page<Student> findAllByPerson_NamesLikeAndEnabled(Pageable pageable, String filterValue, boolean enabled);

    long countByPerson_NamesLikeAndEnabled(String filterValue, boolean enabled);
}
