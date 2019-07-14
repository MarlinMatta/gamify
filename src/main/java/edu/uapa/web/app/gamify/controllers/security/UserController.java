package edu.uapa.web.app.gamify.controllers.security;

import edu.uapa.web.app.gamify.domains.securities.Permission;
import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.services.securities.PermissionService;
import edu.uapa.web.app.gamify.services.securities.UserService;
import edu.uapa.web.app.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.security.PermissionDto;
import edu.utesa.lib.models.dtos.security.UserDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Urls.APP_USER)
public class UserController {

    private final UserService service;
    private final PermissionService permissionService;

    public UserController(UserService service, PermissionService permissionService) {
        this.service = service;
        this.permissionService = permissionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> get(@RequestParam String page, @RequestParam String size, @RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        List<UserDto> result = service.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), "%" + filterValue + "%").stream().map(User::toDto).collect(Collectors.toList());
        System.out.println("User Get Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public ResponseEntity<List<PermissionDto>> getPermissions(@RequestParam String userId, @RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        List<PermissionDto> result = permissionService.findAllPermissions(Long.parseLong(userId), filterValue).stream().map(Permission::toDto).collect(Collectors.toList());
        System.out.println("User Get Permission Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = Urls.COUNT, method = RequestMethod.GET)
    public ResponseEntity<Long> count(@RequestParam String filterValue) {
        long start = System.currentTimeMillis();
        Long result = service.count("%" + filterValue + "%");
        System.out.println("User Count Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> update(@RequestBody UserDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(User.toDomain(dto)) != null) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        System.out.println("User Update Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> save(@RequestBody UserDto dto) {
        long start = System.currentTimeMillis();
        if (service.bootStrap(User.toDomain(dto)) != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        System.out.println("User Save Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity update(@RequestParam String id) {
        long start = System.currentTimeMillis();
        service.softDelete(Long.parseLong(id));
        System.out.println("User Delete Total Time: " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
