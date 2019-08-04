package edu.uapa.web.app.gamify.domains.people;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.uapa.web.app.gamify.domains.locations.Address;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.person.PersonDto;
import edu.utesa.lib.models.enums.person.Gender;
import edu.utesa.lib.models.enums.person.MaritalStatus;
import edu.utesa.lib.models.enums.person.Nationality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "people")
public class Person extends Auditable {
    private String dni;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date birthday;
    @Column(nullable = false)
    private Gender gender;
    @Column(nullable = false)
    private Nationality nationality;
    private MaritalStatus maritalStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    public PersonDto toLazyDto() {
        PersonDto dto = new PersonDto();
        dto.setId(getId());
        dto.setDni(dni);
        dto.setFirstNames(firstName);
        dto.setLastNames(lastName);
        dto.setBirthday(birthday);
        dto.setGender(gender);
        dto.setNationality(nationality);
        dto.setMaritalStatus(maritalStatus);
        dto.setAddressDto(address.toLazyDto());
        return dto;
    }

    public static Person toDomain(PersonDto dto) {
        var domain = new Person();
        domain.setId(dto.getId());
        domain.setDni(dto.getDni());
        domain.setFirstName(dto.getFirstNames());
        domain.setLastName(dto.getLastNames());
        domain.setBirthday(dto.getBirthday());
        domain.setGender(dto.getGender());
        domain.setNationality(dto.getNationality());
        domain.setMaritalStatus(dto.getMaritalStatus());
        domain.address = Address.toDomain(dto.getAddressDto());
        return domain;
    }
}

