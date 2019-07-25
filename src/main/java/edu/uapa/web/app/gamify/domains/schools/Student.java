package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.domains.people.Person;
import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
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

    public Student toDto() {
        Student dto = new Student();
        dto.setId(getId());
        dto.setPerson(person);
        dto.setUser(user);
        return dto;
    }

    public static Student toDomain(Student dto) {
        var student = new Student();
        student.setId(dto.getId());
        student.setPerson(dto.getPerson());
        student.setUser(dto.getUser());
        return student;
    }
}

