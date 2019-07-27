package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.domains.people.Person;
import edu.uapa.web.app.gamify.domains.securities.User;
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
@Entity(name = "teachers")
public class Teacher extends Auditable {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id")
    private Person person;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "lazy_id")
    private User user;

    public Teacher toDto() {
        Teacher dto = new Teacher();
        dto.setId(getId());
        dto.setPerson(person);
        dto.setUser(user);
        return dto;
    }

    public static Teacher toDomain(Teacher dto) {
        var teacher = new Teacher();
        teacher.setId(dto.getId());
        teacher.setPerson(dto.getPerson());
        teacher.setUser(dto.getUser());
        return teacher;
    }
}

