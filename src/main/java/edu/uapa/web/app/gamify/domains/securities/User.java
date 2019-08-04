package edu.uapa.web.app.gamify.domains.securities;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.security.UserDto;
import edu.utesa.lib.models.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
        dto.setPermissions(permissions.stream().map(Permission::toDtoReduce).collect(Collectors.toSet()));
        return dto;
    }

    public static User toDomain(UserDto dto) {
        var domain = new User();
        domain.setId(dto.getId());
        domain.setUserName(dto.getNickName());
        domain.setPassword(dto.getPassword());
        domain.setLanguage(dto.getLanguage());
        domain.setEmail(dto.getMail());
        if (dto.getPermissions() != null)
            domain.setPermissions(dto.getPermissions().stream().map(Permission::toDomain).collect(Collectors.toSet()));
        return domain;
    }
}


