package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.domains.locations.Address;
import edu.uapa.web.app.gamify.domains.people.Person;
import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.TeacherDto;
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
@Entity(name = "teachers")
public class Teacher extends Auditable {
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

    public TeacherDto toLazyDto() {
        TeacherDto dto = new TeacherDto();
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
        return dto;
    }

    public static Teacher toDomain(TeacherDto dto) {
        var domain = new Teacher();
        domain.setId(dto.getId());
        domain.person = Person.toDomain(dto.getPersonDto());
        domain.address = Address.toDomain(dto.getAddressDto());
        domain.school = School.toDomain(dto.getSchoolDto());
        domain.grade = Grade.toDomain(dto.getGradeDto());
        domain.user = User.toDomain(dto.getUserDto());
        return domain;
    }
}


