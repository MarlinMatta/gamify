package edu.uapa.web.app.gamify.controllers.security;

import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.services.securities.PermissionService;
import edu.uapa.web.app.gamify.services.securities.UserService;
import edu.uapa.web.app.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.security.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = Urls.APP_LOGIN)
public class SecurityController {

    private final UserService userService;
    private final PermissionService permissionService;

    public SecurityController(UserService userService, PermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String up() {
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDto login(HttpServletRequest httpServletRequest) {
        long start = System.currentTimeMillis();
        String userName = httpServletRequest.getParameter("userName");
        String password = httpServletRequest.getParameter("password");
        UserDto dto = userService.findByUserNameAndPasswordAndEnabled(userName, password, true)
                .map(User::toDtoWithPermission).orElse(null);
        System.out.println("Login Total Time: " + (System.currentTimeMillis() - start));
        return dto;
    }
}

/*
UserDto userDto = userService.findByUserNameAndPasswordAndEnabled(userName, password, true).map(User::toDto).orElse(null);
        if (userDto != null)
            userDto.setPermissionDtos(permissionService.findAllPermissions(userDto.getId()).stream().map(Permission::toDto).collect(Collectors.toSet()));
        return userDto;

          return userService.findByUserNameAndPasswordAndEnabled(userName, password, true).map(user -> {
            user.setPermissions(new HashSet<>(permissionService.findAllPermissions(user.getId())));
            return user.toDto();
        }).orElse(null);
*/