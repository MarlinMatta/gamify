package edu.uapa.web.app.gamify.utils.bootstrap;

import edu.uapa.web.app.gamify.domains.securities.Permission;
import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.models.interfaces.BootStrapInsert;
import edu.uapa.web.app.gamify.services.securities.PermissionGroupService;
import edu.uapa.web.app.gamify.services.securities.UserService;
import edu.utesa.lib.models.enums.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class BasicUser implements BootStrapInsert {

    private final UserService service;
    private final PermissionGroupService permissionGroupService;

    @Autowired
    public BasicUser(PermissionGroupService permissionGroupService, UserService service) {
        this.permissionGroupService = permissionGroupService;
        this.service = service;
    }

    @Override
    public void insert() {
        create("root", "root@gmail.com", "root", Language.ENGLISH, true, rootPermissions());
        create("game", "game@gmail.com", "game", Language.ENGLISH, false, gamePermissions());
        create("admin", "admin@gmail.com", "admin", Language.SPANISH, false, adminPermissions());
    }

    private void create(String userName, String email, String password, Language language, boolean isAdmin, Set<Permission> permissions) {
        Optional<User> optional = service.login(userName, email, userName);
        if (optional.isEmpty()) {
            User user = new User();
            user.setUserName(userName);
            user.setEmail(email);
            user.setPassword(password);
            user.setLanguage(language);
            user.setAdmin(isAdmin);
            user.setPermissions(permissions);
            service.bootStrap(user);
        }
    }

    private Set<Permission> rootPermissions() {
        Set<Permission> permissions = new HashSet<>();
        permissionGroupService.findByName("SYSTEM_USER").ifPresent(it -> permissions.addAll(it.getEagerPermissions()));
        return permissions;
    }

    private Set<Permission> gamePermissions() {
        Set<Permission> permissions = new HashSet<>();
        permissionGroupService.findByName("GAME").ifPresent(it -> permissions.addAll(it.getEagerPermissions()));
        return permissions;
    }

    private Set<Permission> adminPermissions() {
        Set<Permission> permissions = new HashSet<>();
        permissionGroupService.findByName("ADMIN").ifPresent(it -> permissions.addAll(it.getEagerPermissions()));
        return permissions;
    }
}
