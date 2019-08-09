package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.domains.locations.Address;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.SchoolDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "schools")
public class School extends Auditable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String district;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    public SchoolDto toLazyDto() {
        SchoolDto dto = new SchoolDto();
        dto.setId(getId());
        dto.setName(name);
        dto.setDistrict(district);
        return dto;
    }

    public SchoolDto toEagerDto() {
        SchoolDto dto = new SchoolDto();
        dto.setId(getId());
        dto.setName(name);
        dto.setDistrict(district);
        dto.setAddressDto(address.toLazyDto());
        return dto;
    }

    public static School toDomain(SchoolDto dto) {
        var domain = new School();
        domain.setId(dto.getId());
        domain.setName(dto.getName());
        domain.setDistrict(dto.getDistrict());
        domain.address = Address.toDomain(dto.getAddressDto());
        return domain;
    }
}
