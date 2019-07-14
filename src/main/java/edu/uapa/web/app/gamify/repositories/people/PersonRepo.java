package edu.uapa.web.app.gamify.repositories.people;

import edu.uapa.web.app.gamify.domains.people.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
}
