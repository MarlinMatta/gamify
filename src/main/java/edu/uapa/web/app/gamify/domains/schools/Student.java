package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.domains.people.Person;
import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "students")
public class Student extends Auditable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lazy_id")
    private User user;

    public StudentDto toDto() {
        StudentDto dto = new StudentDto();
        dto.setId(getId());
        dto.setPersonDto(person.toLazyDto());
        dto.setUserDto(user.toDto());
        return dto;
    }

    public static Student toDomain(StudentDto dto) {
        var student = new Student();
        student.setId(dto.getId());
        student.setPerson(Person.toDomain(dto.getPersonDto()));
        student.setUser(User.toDomain(dto.getUserDto()));
        return student;
    }
}

