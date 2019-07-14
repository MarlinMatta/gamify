package edu.uapa.web.app.gamify.repositories.securities;

import edu.uapa.web.app.gamify.domains.securities.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParameterRepo extends JpaRepository<Parameter, Long> {
    Optional<Parameter> findByCodeAndEnabled(Integer code, boolean enabled);

    Page<Parameter> findAllByNameLikeAndEnabled(Pageable pageable, String filterValue, boolean enabled);

    long countByNameLikeAndEnabled(String filterValue, boolean enabled);
}
