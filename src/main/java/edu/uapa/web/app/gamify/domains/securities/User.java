package edu.uapa.web.app.gamify.domains.securities;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.security.UserDto;
import edu.utesa.lib.models.enums.Language;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "users")
public class User extends Auditable {
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Language language;
    private Boolean isAdmin = false;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_permissions", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    public UserDto toDto() {
        var dto = new UserDto();
        dto.setId(getId());
        dto.setNickName(userName);
        dto.setPassword(password);
        dto.setMail(email);
        dto.setLanguage(getLanguage());
        dto.setAdmin(isAdmin);
        return dto;
    }

    public UserDto toDtoWithPermission() {
        var dto = new UserDto();
        dto.setId(getId());
        dto.setNickName(userName);
        dto.setPassword(password);
        dto.setMail(email);
        dto.setLanguage(getLanguage());
        dto.setAdmin(isAdmin);
        dto.setPermissionDtos(permissions.stream().map(Permission::toDtoReduce).collect(Collectors.toSet()));
        return dto;
    }

    public static User toDomain(UserDto dto) {
        var domain = new User();
        domain.setId(dto.getId());
        domain.setUserName(dto.getNickName());
        domain.setPassword(dto.getPassword());
        domain.setLanguage(dto.getLanguage());
        domain.setEmail(dto.getMail());
        domain.setPermissions(dto.getPermissionDtos().stream().map(Permission::toDomain).collect(Collectors.toSet()));
        return domain;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}


