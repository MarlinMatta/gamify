package edu.uapa.web.app.gamify.controllers.school;

import edu.uapa.web.app.gamify.domains.schools.Subject;
import edu.uapa.web.app.gamify.services.school.SubjectService;
import edu.uapa.web.app.gamify.utils.Urls;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Urls.APP_SUBJECT)
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Subject>> get(@RequestParam String page, @RequestParam String size, @RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        List<Subject> result = service.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), "%" + filterValue + "%").stream().map(Subject::toDto).collect(Collectors.toList());
        System.out.println("Subject Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = Urls.COUNT, method = RequestMethod.GET)
    public ResponseEntity<Long> count(@RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        Long result = service.count("%" + filterValue + "%");
        System.out.println("Subject Count Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Subject> update(@RequestBody Subject Subject) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Subject.toDomain(Subject)) != null) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        System.out.println("Subject Update Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Subject> save(@RequestBody Subject Subject) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Subject.toDomain(Subject)) != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        System.out.println("Subject Save Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity update(@RequestParam String id) {
        long start = System.currentTimeMillis();
        service.softDelete(Long.parseLong(id));
        System.out.println("Subject Delete Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
