package edu.uapa.web.app.gamify.services.people;

import edu.uapa.web.app.gamify.domains.people.Person;
import edu.uapa.web.app.gamify.repositories.people.PersonRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
class PersonService {

    private final PersonRepo repository;

    public PersonService(PersonRepo repository) {
        this.repository = repository;
    }

    private Person merge(Person item, String userName) {
        if (item != null) {
            if (repository.existsById(item.getId())) {
                item.setLastModifiedDate(new Date());
                item.setLastModifiedBy(userName);
            } else {
                item.setCreatedBy(userName);
                item.setCreatedDate(new Date());
            }
            return repository.save(item);
        }
        return null;
    }

    Person bootStrap(Person item) {
        return merge(item, Constants.SYSTEM_USER);
    }
}