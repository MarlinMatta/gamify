package edu.uapa.web.app.gamify.repositories.securities;

import edu.uapa.web.app.gamify.domains.securities.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Long> {

    Optional<Permission> findByCodeAndEnabled(Integer code, boolean enabled);

    Page<Permission> findAllByNameLikeAndEnabled(Pageable pageable, String filter, boolean enabled);

    long countByNameLikeAndEnabled(String filter, boolean enabled);

    @Query(value = "SELECT p.*" +
            " FROM users u" +
            "   JOIN user_permissions up ON u.id = up.user_id" +
            "   JOIN permissions p ON up.permission_id = p.id" +
            " WHERE u.id=?1 and p.name like '%%'", nativeQuery = true)
    List<Permission> findAllPermissions(@Param("userId") Long userId, @Param("filterValue") String filterValue);
}
