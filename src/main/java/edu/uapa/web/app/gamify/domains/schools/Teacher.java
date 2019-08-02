package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.domains.people.Person;
import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "teachers")
public class Teacher extends Auditable {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id")
    private Person person;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "lazy_id")
    private User user;

    public TeacherDto toDto() {
        TeacherDto dto = new TeacherDto();
        dto.setId(getId());
        dto.setPersonDto(person.toEagerDto());
        dto.setUserDto(user.toDto());
        return dto;
    }

    public static Teacher toDomain(TeacherDto dto) {
        var teacher = new Teacher();
        teacher.setId(dto.getId());
        teacher.setPerson(Person.toDomain(dto.getPersonDto()));
        teacher.setUser(User.toDomain(dto.getUserDto()));
        return teacher;
    }
}

