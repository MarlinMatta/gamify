package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.SubjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "subjects")
public class Subject extends Auditable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_id")
    private Grade grade;
    @Column(nullable = false)
    private String name;
    private String description;

    public SubjectDto toLazyDto() {
        SubjectDto dto = new SubjectDto();
        dto.setId(getId());
        dto.setTeacherDto(teacher.toLazyDto());
        dto.setGradeDto(grade.toLazyDto());
        dto.setName(name);
        dto.setDescription(description);
        return dto;
    }

    public static Subject toDomain(SubjectDto dto) {
        var subject = new Subject();
        subject.setId(dto.getId());
        subject.teacher = Teacher.toDomain(dto.getTeacherDto());
        subject.grade = Grade.toDomain(dto.getGradeDto());
        subject.setName(dto.getName());
        subject.setDescription(dto.getDescription());
        return subject;
    }
}
