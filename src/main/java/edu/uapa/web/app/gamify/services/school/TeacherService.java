package edu.uapa.web.app.gamify.services.school;

import edu.uapa.web.app.gamify.domains.schools.Teacher;
import edu.uapa.web.app.gamify.repositories.school.TeacherRepo;
import edu.uapa.web.app.gamify.services.address.AddressService;
import edu.uapa.web.app.gamify.services.people.PersonService;
import edu.uapa.web.app.gamify.services.securities.UserService;
import edu.uapa.web.app.gamify.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepo repository;
    private final SchoolService schoolService;
    private final AddressService addressService;
    private final GradeService gradeService;
    private final PersonService personService;
    private final UserService userService;

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
        if (item.getAddress().getId() != null) {
            item.setAddress(addressService.bootStrap(item.getAddress()));
        }
        if (item.getSchool().getId() != null) {
            item.setSchool(schoolService.bootStrap(item.getSchool()));
        }
        if (item.getGrade().getId() != null) {
            item.setGrade(gradeService.bootStrap(item.getGrade()));
        }
        if (item.getUser().getId() != null) {
            item.setUser(userService.bootStrap(item.getUser()));
        }
        return merge(item, Constants.SYSTEM_USER);
    }

    public Page<Teacher> findAll(Pageable pageable, String filterValue) {
        return repository.findAllByPersonFirstNameLikeAndEnabled(pageable, filterValue, true);
    }

    public List<Teacher> findAll() {
        return repository.findAll();
    }

    public long count(String filterValue) {
        return repository.countByPersonFirstNameLikeAndEnabled(filterValue, true);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(item -> {
            item.setEnabled(false);
            bootStrap(item);
        });
    }
}