package edu.uapa.web.app.gamify.domains.securities;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.security.PermissionDto;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "permissions")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Permission extends Auditable {
    @Column(nullable = false, unique = true)
    private int code;
//    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    PermissionDto toDtoReduce() {
        var dto = new PermissionDto();
        dto.setCode(code);
        return dto;
    }

    public PermissionDto toDto() {
        var dto = new PermissionDto();
        dto.setId(getId());
        dto.setCode(code);
        dto.setName(name);
        dto.setDescription(description);
        return dto;
    }

    public static Permission toDomain(PermissionDto dto) {
        var permission = new Permission();
        permission.setId(dto.getId());
        permission.setCode(dto.getCode());
        permission.setName(dto.getName());
        permission.setDescription(dto.getDescription());
        return permission;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}