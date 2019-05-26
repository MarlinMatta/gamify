package edu.uapa.web.app.gamify.repositories.securities;

import edu.uapa.web.app.gamify.domains.securities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
