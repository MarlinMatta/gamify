package edu.uapa.web.app.gamify.domains.securities;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.security.PermissionGroupDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "permission_groups")
public class PermissionGroup extends Auditable {
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "permission_group_permissions",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    public PermissionGroupDto toDto() {
        var dto = new PermissionGroupDto();
        dto.setId(getId());
        dto.setName(name);
        dto.setDescription("");
        return dto;
    }

    public static PermissionGroup toDomain(PermissionGroupDto dto) {
        var domain = new PermissionGroup();
        domain.setId(dto.getId());
        domain.setName(dto.getName());
        return domain;
    }

    public PermissionGroup() {
        permissions = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<Permission> getEagerPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);
    }
}