package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "grades")
public class Grade extends Auditable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    private School school;
    @Column(nullable = false)
    private String name;
    private String description;

    public Grade toDto() {
        Grade dto = new Grade();
        dto.setId(getId());
        dto.setSchool(school);
        dto.setName(name);
        dto.setDescription(description);
        return dto;
    }

    public static Grade toDomain(Grade dto) {
        var grade = new Grade();
        grade.setId(dto.getId());
        grade.setSchool(dto.getSchool());
        grade.setName(dto.getName());
        grade.setDescription(dto.getDescription());
        return grade;
    }
}


