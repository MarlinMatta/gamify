package edu.uapa.web.app.gamify.controllers.school;

import edu.uapa.web.app.gamify.domains.schools.Teacher;
import edu.uapa.web.app.gamify.services.school.TeacherService;
import edu.uapa.web.app.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.school.TeacherDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Urls.APP_TEACHER)
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TeacherDto>> get(@RequestParam String page, @RequestParam String size, @RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        List<TeacherDto> result = service.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), "%" + filterValue + "%").stream().map(Teacher::toDto).collect(Collectors.toList());
        System.out.println("Teacher Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<TeacherDto> refresh(@RequestParam String id) {
        long start = System.currentTimeMillis();
        TeacherDto employee = service.refresh(Long.parseLong(id)).map(Teacher::toDto).orElse(null);
        System.out.println("Teacher Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = Urls.COUNT, method = RequestMethod.GET)
    public ResponseEntity<Long> count(@RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        Long result = service.count("%" + filterValue + "%");
        System.out.println("Teacher Count Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TeacherDto> update(@RequestBody TeacherDto studentDto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Teacher.toDomain(studentDto)) != null) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        System.out.println("Teacher Update Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TeacherDto> save(@RequestBody TeacherDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Teacher.toDomain(dto)) != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        System.out.println("Teacher Save Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity update(@RequestParam String id) {
        long start = System.currentTimeMillis();
        service.softDelete(Long.parseLong(id));
        System.out.println("Teacher Delete Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
