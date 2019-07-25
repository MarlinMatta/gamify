package edu.uapa.web.app.gamify.repositories.school;

import edu.uapa.web.app.gamify.domains.schools.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepo extends JpaRepository<School, Long> {
    Optional<School> findByDistrictAndEnabled(String district, boolean enabled);

    Page<School> findAllByNameLikeAndEnabled(Pageable pageable, String filterValue, boolean enabled);

    long countByNameLikeAndEnabled(String filterValue, boolean enabled);
}
