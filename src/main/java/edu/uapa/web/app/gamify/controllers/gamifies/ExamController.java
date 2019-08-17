package edu.uapa.web.app.gamify.controllers.gamifies;

import edu.uapa.web.app.gamify.domains.gamifies.Exam;
import edu.uapa.web.app.gamify.domains.gamifies.Problem;
import edu.uapa.web.app.gamify.services.gamifies.ExamService;
import edu.uapa.web.app.gamify.utils.Constants;
import edu.uapa.web.app.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.school.ExamDto;
import edu.utesa.lib.models.dtos.school.ProblemDto;
import edu.utesa.lib.models.dtos.school.TeacherDto;
import edu.utesa.lib.models.enums.GameDifficulty;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value = Urls.APP_EXAM)
public class ExamController {

    private final ExamService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ExamDto>> get(@RequestParam String page, @RequestParam String size, @RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        List<ExamDto> result = service.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), "%" + filterValue + "%").stream().map(Exam::toLazyDto).collect(Collectors.toList());
        System.out.println("Exam Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public ResponseEntity<ExamDto> getById(@RequestParam String id) {
        long start = System.currentTimeMillis();
        ExamDto result = service.findById(Long.parseLong(id)).toLazyDto();
        System.out.println("Student Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = Urls.COUNT, method = RequestMethod.GET)
    public ResponseEntity<Long> count(@RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        Long result = service.count("%" + filterValue + "%");
        System.out.println("Exam Count Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ExamDto> update(@RequestBody ExamDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Exam.toDomain(dto)) != null) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        System.out.println("Exam Update Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ExamDto> save(@RequestBody ExamDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Exam.toDomain(dto)) != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        System.out.println("Exam Save Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<ExamDto>> getAll() {
        long start = System.currentTimeMillis();
        List<ExamDto> result = service.findAll().stream().map(Exam::toLazyDto).collect(Collectors.toList());
        System.out.println("Exam Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/filtered", method = RequestMethod.GET)
    public ResponseEntity<List<ExamDto>> getAllFiltered(@RequestParam String subjectId,
                                                        @RequestParam String topicId) {
        long start = System.currentTimeMillis();
        List<ExamDto> result = service.findAllFiltered(Long.parseLong(subjectId),
                Long.parseLong(topicId)).stream().map(Exam::toLazyDto).collect(Collectors.toList());
        System.out.println("Exam Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity update(@RequestParam String id) {
        long start = System.currentTimeMillis();
        service.softDelete(Long.parseLong(id));
        System.out.println("Exam Delete Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
