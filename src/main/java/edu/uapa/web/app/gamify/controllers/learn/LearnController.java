package edu.uapa.web.app.gamify.controllers.learn;

import edu.uapa.web.app.gamify.domains.learn.Learn;
import edu.uapa.web.app.gamify.services.learn.LearnService;
import edu.uapa.web.app.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.school.LearnDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value = Urls.APP_EXAM)
public class LearnController {

    private final LearnService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<LearnDto> getByTopicId(String topicId) {
        long start = System.currentTimeMillis();
        LearnDto result = service.findByTopic(Long.parseLong(topicId)).map(Learn::toLazyDto).orElse(null);
        System.out.println("Learn Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<LearnDto>> get(@RequestParam String page, @RequestParam String size, @RequestParam String filterValue) {
//        long start = System.currentTimeMillis();
//        List<LearnDto> result = service.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), "%" + filterValue + "%").stream().map(Learn::toLazyDto).collect(Collectors.toList());
//        System.out.println("Learn Get Total Time: " + (System.currentTimeMillis() - start));
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

//    @RequestMapping(value = Urls.COUNT, method = RequestMethod.GET)
//    public ResponseEntity<Long> count(@RequestParam String filterValue) {
//        long start = System.currentTimeMillis();
//        Long result = service.count("%" + filterValue + "%");
//        System.out.println("Learn Count Total Time: " + (System.currentTimeMillis() - start));
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LearnDto> update(@RequestBody LearnDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Learn.toDomain(dto)) != null) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        System.out.println("Learn Update Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LearnDto> save(@RequestBody LearnDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Learn.toDomain(dto)) != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        System.out.println("Learn Save Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<LearnDto>> getAll() {
        long start = System.currentTimeMillis();
        List<LearnDto> result = service.findAll().stream().map(Learn::toLazyDto).collect(Collectors.toList());
        System.out.println("School Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity update(@RequestParam String id) {
        long start = System.currentTimeMillis();
        service.softDelete(Long.parseLong(id));
        System.out.println("Learn Delete Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
