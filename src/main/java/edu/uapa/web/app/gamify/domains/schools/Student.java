package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.domains.locations.Address;
import edu.uapa.web.app.gamify.domains.people.Person;
import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    private School school;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_id")
    private Grade grade;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private int points;

    public StudentDto toLazyDto() {
        StudentDto dto = new StudentDto();
        dto.setId(getId());
        if (person != null)
            dto.setPersonDto(person.toLazyDto());
        if (address != null)
            dto.setAddressDto(address.toLazyDto());
        if (school != null)
            dto.setSchoolDto(school.toEagerDto());
        if (grade != null)
            dto.setGradeDto(grade.toLazyDto());
        if (user != null)
            dto.setUserDto(user.toDtoWithPermission());
        if (points >= 0)
            dto.setPoints(points);
        return dto;
    }

    public static Student toDomain(StudentDto dto) {
        var domain = new Student();
        domain.setId(dto.getId());
        domain.person = Person.toDomain(dto.getPersonDto());
        domain.address = Address.toDomain(dto.getAddressDto());
        domain.school = School.toDomain(dto.getSchoolDto());
        domain.grade = Grade.toDomain(dto.getGradeDto());
        domain.user = User.toDomain(dto.getUserDto());
        domain.points = dto.getPoints();
        return domain;
    }
}


