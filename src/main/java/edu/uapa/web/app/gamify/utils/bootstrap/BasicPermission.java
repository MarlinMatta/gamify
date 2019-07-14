package edu.uapa.web.app.gamify.utils.bootstrap;

import edu.uapa.web.app.gamify.domains.securities.Permission;
import edu.uapa.web.app.gamify.models.enums.EnumPermission;
import edu.uapa.web.app.gamify.models.interfaces.BootStrapInsert;
import edu.uapa.web.app.gamify.services.securities.PermissionService;
import edu.uapa.web.app.gamify.utils.security.SystemPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BasicPermission implements BootStrapInsert {

    private final PermissionService service;

    @Autowired
    public BasicPermission(PermissionService service) {
        this.service = service;
    }

    @Override
    public void insert() {
        for (EnumPermission enumPermission : EnumPermission.values()) {
            Optional<Permission> optional = service.findByCode(enumPermission.code);
            if (optional.isEmpty()) {
                Permission permission = new Permission();
                permission.setCode(enumPermission.code);
                permission.setName(SystemPermission.name(enumPermission));
                permission.setDescription(SystemPermission.description(enumPermission));
                service.bootStrap(permission);
            }
        }
    }
}
