package edu.uapa.web.app.gamify.utils.bootstrap;

import edu.uapa.web.app.gamify.domains.securities.PermissionGroup;
import edu.uapa.web.app.gamify.models.enums.EnumPermission;
import edu.uapa.web.app.gamify.models.interfaces.BootStrapInsert;
import edu.uapa.web.app.gamify.services.securities.PermissionGroupService;
import edu.uapa.web.app.gamify.services.securities.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class BasicPermissionGroup implements BootStrapInsert {

    private final PermissionGroupService service;
    private final PermissionService permissionService;

    @Autowired
    public BasicPermissionGroup(PermissionGroupService service, PermissionService permissionService) {
        this.service = service;
        this.permissionService = permissionService;
    }

    @Override
    public void insert() {
        create("SYSTEM_USER", rootPermissions());
        create("GAME", gamePermissions());
        create("ADMIN", adminPermissions());
    }

    private void create(String name, List<EnumPermission> permissions) {
        Optional<PermissionGroup> optional = service.findByName(name);
        if (optional.isEmpty()) {
            PermissionGroup permissionGroup = new PermissionGroup();
            permissionGroup.setName(name);
            permissions.forEach(permission -> permissionService.findByCode(permission.code).ifPresent(permissionGroup::addPermission));
            service.bootStrap(permissionGroup);
        }
    }

    private List<EnumPermission> rootPermissions() {
        return Collections.singletonList(EnumPermission.ROOT);
    }

    private List<EnumPermission> gamePermissions() {
        return Collections.singletonList(EnumPermission.GAME);
    }

    private List<EnumPermission> adminPermissions() {
        return Collections.singletonList(EnumPermission.ADMIN);
    }
}
