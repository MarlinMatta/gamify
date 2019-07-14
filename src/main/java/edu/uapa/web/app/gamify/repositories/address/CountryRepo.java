package edu.uapa.web.app.gamify.repositories.address;

import edu.uapa.web.app.gamify.domains.locations.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {
    Optional<Country> findByNameAndEnabled(String name, boolean enabled);
}
