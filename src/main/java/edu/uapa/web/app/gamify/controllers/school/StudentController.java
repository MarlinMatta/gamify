package edu.uapa.web.app.gamify.controllers.school;

import edu.uapa.web.app.gamify.domains.schools.Student;
import edu.uapa.web.app.gamify.services.school.StudentService;
import edu.uapa.web.app.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.school.StudentDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Urls.APP_STUDENT)
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StudentDto>> get(@RequestParam String page, @RequestParam String size, @RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        List<StudentDto> result = service.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), "%" + filterValue + "%").stream().map(Student::toDto).collect(Collectors.toList());
        System.out.println("Student Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<StudentDto> refresh(@RequestParam String id) {
        long start = System.currentTimeMillis();
        StudentDto employee = service.refresh(Long.parseLong(id)).map(Student::toDto).orElse(null);
        System.out.println("Student Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = Urls.COUNT, method = RequestMethod.GET)
    public ResponseEntity<Long> count(@RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        Long result = service.count("%" + filterValue + "%");
        System.out.println("Student Count Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudentDto> update(@RequestBody StudentDto studentDto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Student.toDomain(studentDto)) != null) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        System.out.println("Student Update Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudentDto> save(@RequestBody StudentDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Student.toDomain(dto)) != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        System.out.println("Student Save Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity update(@RequestParam String id) {
        long start = System.currentTimeMillis();
        service.softDelete(Long.parseLong(id));
        System.out.println("Student Delete Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
