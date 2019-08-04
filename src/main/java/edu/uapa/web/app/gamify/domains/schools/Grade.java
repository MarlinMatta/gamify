package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.GradeDto;
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

    public GradeDto toLazyDto() {
        GradeDto dto = new GradeDto();
        dto.setId(getId());
        dto.setSchoolDto(school.toEagerDto());
        dto.setName(name);
        dto.setDescription(description);
        return dto;
    }

    public static Grade toDomain(GradeDto dto) {
        var domain = new Grade();
        domain.setId(dto.getId());
        domain.school = School.toDomain(dto.getSchoolDto());
        domain.setName(dto.getName());
        domain.setDescription(dto.getDescription());
        return domain;
    }
}


