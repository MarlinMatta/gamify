package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "subjects")
public class Subject extends Auditable {
    @Column(nullable = false)
    private String name;
    private String description;

    public Subject toDto() {
        Subject dto = new Subject();
        dto.setId(getId());
        dto.setName(name);
        dto.setDescription(description);
        return dto;
    }

    public static Subject toDomain(Subject dto) {
        var subject = new Subject();
        subject.setId(dto.getId());
        subject.setName(dto.getName());
        subject.setDescription(dto.getDescription());
        return subject;
    }
}
