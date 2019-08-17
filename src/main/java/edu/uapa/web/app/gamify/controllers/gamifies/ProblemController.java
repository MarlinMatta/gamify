package edu.uapa.web.app.gamify.controllers.gamifies;

import edu.uapa.web.app.gamify.domains.gamifies.Problem;
import edu.uapa.web.app.gamify.services.gamifies.ProblemService;
import edu.uapa.web.app.gamify.utils.Constants;
import edu.uapa.web.app.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.school.ProblemDto;
import edu.utesa.lib.models.enums.GameDifficulty;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value = Urls.APP_PROBLEM)
public class ProblemController {

    private final ProblemService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProblemDto>> get(@RequestParam String page, @RequestParam String size, @RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        List<ProblemDto> result = service.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), "%" + filterValue + "%").stream().map(Problem::toLazyDto).collect(Collectors.toList());
        System.out.println("Problem Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ProblemDto>> getAll(@RequestParam String difficulty, @RequestParam String topicId, @RequestParam String size) {
        long start = System.currentTimeMillis();
        List<ProblemDto> result = service.findAll(PageRequest.of(0, Integer.parseInt(size)), GameDifficulty.valueOf(difficulty), Long.parseLong(topicId)).stream().map(Problem::toLazyDto).collect(Collectors.toList());
        System.out.println("Problem Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/exam", method = RequestMethod.GET)
    public ResponseEntity<List<ProblemDto>> getAllByExam(@RequestParam String difficulty, @RequestParam String size,
                                                         @RequestParam String teacherId, @RequestParam String topicId,
                                                         @RequestParam String from, @RequestParam String to) throws ParseException {
        long start = System.currentTimeMillis();
        List<ProblemDto> result = service.findAllByGameDifficultyAndTeacher_IdAndTopic_IdAndEnabledAndCreatedDateBetween
                (PageRequest.of(0, Integer.parseInt(size)), GameDifficulty.valueOf(difficulty),
                        Long.parseLong(teacherId), Long.parseLong(topicId), Constants.simpleDateFormat.parse(from), Constants.simpleDateFormat.parse(to)).stream()
                .map(Problem::toLazyDto).collect(Collectors.toList());
        System.out.println("Problem Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = Urls.COUNT, method = RequestMethod.GET)
    public ResponseEntity<Long> count(@RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        Long result = service.count("%" + filterValue + "%");
        System.out.println("Problem Count Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProblemDto> update(@RequestBody ProblemDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Problem.toDomain(dto)) != null) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        System.out.println("Problem Update Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProblemDto> save(@RequestBody ProblemDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Problem.toDomain(dto)) != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        System.out.println("Problem Save Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<ProblemDto>> getAll() {
        long start = System.currentTimeMillis();
        List<ProblemDto> result = service.findAll().stream().map(Problem::toLazyDto).collect(Collectors.toList());
        System.out.println("School Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity update(@RequestParam String id) {
        long start = System.currentTimeMillis();
        service.softDelete(Long.parseLong(id));
        System.out.println("Problem Delete Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
