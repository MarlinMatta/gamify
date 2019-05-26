package edu.uapa.web.app.gamify.repositories.address;

import edu.uapa.web.app.gamify.domains.locations.Address;
import edu.uapa.web.app.gamify.domains.securities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
}
