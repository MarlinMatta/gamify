package edu.uapa.web.app.gamify.domains.people;

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
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "people")
public class Person extends Auditable {
    @Column(nullable = false)
    private String names;
    @Column(nullable = false)
    private String lastNames;
    @Column(nullable = false)
    private Date birth;
    @Column(nullable = false)
    private Gender gender;
    private String dni;
    private Nationality nationality;
    private MaritalStatus maritalStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    public static Person toDomain(PersonDto dto) {
        var domain = new Person();
        domain.names = dto.getFirstNames();
        domain.lastNames = dto.getLastNames();
        domain.birth = dto.getBirthDay();
        domain.gender = dto.getGender();
        domain.dni = dto.getDni();
        domain.nationality = dto.getNationality();
        domain.maritalStatus = dto.getMaritalStatus();
        domain.address = Address.toDomain(dto.getAddressDto());
        return domain;
    }

    public PersonDto toLazyDto() {
        var dto = new PersonDto();
        dto.setId(getId());
        dto.setFirstNames(names);
        dto.setLastNames(lastNames);
        dto.setBirthDay(birth);
        dto.setGender(gender);
        dto.setDni(dni);
        dto.setNationality(nationality);
        dto.setMaritalStatus(maritalStatus);
        return dto;
    }

    public PersonDto toEagerDto() {
        var dto = new PersonDto();
        dto.setId(getId());
        dto.setFirstNames(names);
        dto.setLastNames(lastNames);
        dto.setBirthDay(birth);
        dto.setGender(gender);
        dto.setDni(dni);
        dto.setNationality(nationality);
        dto.setMaritalStatus(maritalStatus);
        dto.setAddressDto(address.toLazyDto());
        return dto;
    }
}

