package edu.uapa.web.app.gamify.repositories.securities;

import edu.uapa.web.app.gamify.domains.securities.PermissionGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionGroupRepo extends JpaRepository<PermissionGroup, Long> {
    Optional<PermissionGroup> findByNameAndEnabled(String name, boolean enabled);

    Page<PermissionGroup> findAllByNameLikeAndEnabled(Pageable pageable, String filter, boolean enabled);

    long countByNameLikeAndEnabled(String filter, boolean enabled);
}
