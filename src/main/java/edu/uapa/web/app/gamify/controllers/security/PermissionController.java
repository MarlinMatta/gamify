package edu.uapa.web.app.gamify.controllers.security;

import edu.uapa.web.app.gamify.domains.securities.Permission;
import edu.uapa.web.app.gamify.domains.securities.PermissionGroup;
import edu.uapa.web.app.gamify.services.securities.PermissionGroupService;
import edu.uapa.web.app.gamify.services.securities.PermissionService;
import edu.uapa.web.app.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.security.PermissionDto;
import edu.utesa.lib.models.dtos.security.PermissionGroupDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Urls.APP_PERMISSION)
public class PermissionController {

    private final PermissionService service;
    private final PermissionGroupService groupService;

    public PermissionController(PermissionService service, PermissionGroupService groupService) {
        this.service = service;
        this.groupService = groupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PermissionDto>> get(@RequestParam String page, @RequestParam String size, @RequestParam String filterValue) {
        List<PermissionDto> result = service.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), "%" + filterValue + "%").stream().map(Permission::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ResponseEntity<List<PermissionDto>> getStudent() {
        List<PermissionDto> result = service.findByCode(1).stream().map(Permission::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ResponseEntity<List<PermissionDto>> getTeacher() {
        List<PermissionDto> result = service.findByCode(2).stream().map(Permission::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ResponseEntity<List<PermissionGroupDto>> getGroup(@RequestParam String page, @RequestParam String size, @RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        List<PermissionGroupDto> result = groupService.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), "%" + filterValue + "%").stream().map(PermissionGroup::toDto).collect(Collectors.toList());
        System.out.println("Permission Grade Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = Urls.COUNT, method = RequestMethod.GET)
    public Long countGroup(@RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        Long result = service.count("%" + filterValue + "%");
        System.out.println("Permission Count Total Time: " + (System.currentTimeMillis() - start));
        return result;
    }

    @RequestMapping(value = "/groups" + Urls.COUNT, method = RequestMethod.GET)
    public Long count(@RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        Long result = groupService.count("%" + filterValue + "%");
        System.out.println("Permission Grade Count Total Time: " + (System.currentTimeMillis() - start));
        return result;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PermissionDto> update(@RequestBody PermissionDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Permission.toDomain(dto)) != null)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        System.out.println("Permission Update Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PermissionGroupDto> updateGroup(@RequestBody PermissionGroupDto dto) {
        long start = System.currentTimeMillis();
        if (groupService.bootStrap(PermissionGroup.toDomain(dto)) != null)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        System.out.println("Permission Grade Update Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PermissionDto> save(@RequestBody PermissionDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(Permission.toDomain(dto)) != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        System.out.println("Permission Save Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PermissionGroupDto> saveGroup(@RequestBody PermissionGroupDto dto) {
        long start = System.currentTimeMillis();
        if (groupService.bootStrap(PermissionGroup.toDomain(dto)) != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        System.out.println("Permission Grade Save Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestParam String id) {
        long start = System.currentTimeMillis();
        service.softDelete(Long.parseLong(id));
        System.out.println("Permission Delete Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.DELETE)
    public ResponseEntity deleteGroup(@RequestParam String id) {
        long start = System.currentTimeMillis();
        groupService.softDelete(Long.parseLong(id));
        System.out.println("Permission Grade Delete Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //@RequestHeader("accept-language") String language
    //@RequestHeader MultiValueMap<String, String> headers
    //@RequestHeader Map<String, String> headers
}
