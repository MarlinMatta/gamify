package edu.uapa.web.app.gamify.repositories.address;

import edu.uapa.web.app.gamify.domains.locations.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
}
