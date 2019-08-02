package edu.uapa.web.app.gamify.services.school;

import edu.uapa.web.app.gamify.domains.schools.Teacher;
import edu.uapa.web.app.gamify.repositories.school.TeacherRepo;
import edu.uapa.web.app.gamify.services.people.PersonService;
import edu.uapa.web.app.gamify.services.securities.UserService;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepo repository;
    private final PersonService personService;
    private final UserService userService;

    public TeacherService(TeacherRepo repository, PersonService personService, UserService userService) {
        this.repository = repository;
        this.personService = personService;
        this.userService = userService;
    }

    private Teacher merge(Teacher item, String userName) {
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

    public Teacher bootStrap(Teacher item) {
        if (item.getPerson().getId() != null) {
            item.setPerson(personService.bootStrap(item.getPerson()));
        }
        if (item.getUser().getId() != null) {
            item.setUser(userService.bootStrap(item.getUser()));
        }
        return merge(item, Constants.SYSTEM_USER);
    }

    public Page<Teacher> findAll(Pageable pageable, String filterValue) {
        return repository.findAllByPerson_NamesLikeAndEnabled(pageable, filterValue, true);
    }

    public List<Teacher> findAll() {
        return repository.findAll();
    }

    public Optional<Teacher> refresh(long id) {
        return repository.findById(id);
    }

    public long count(String filterValue) {
        return repository.countByPerson_NamesLikeAndEnabled(filterValue, true);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(item -> {
            item.setEnabled(false);
            bootStrap(item);
        });
    }
}