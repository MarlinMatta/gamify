package edu.uapa.web.app.gamify.domains.locations;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.location.AddressDto;
import edu.utesa.lib.models.dtos.location.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "addresses")
public class Address extends Auditable {
    //Este campo es EAGER por que no hay una QueryTab para address y los query n+1 no seran continuos
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String name = "Principal";
    @Column(nullable = false)
    private String sector;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String address;

    public AddressDto toLazyDto() {
        AddressDto dto = new AddressDto();
        dto.setId(getId());
        dto.setCountryDto(country.toLazyDto());
        dto.setCity(city);
        dto.setName(name);
        dto.setSector(sector);
        dto.setZipCode(zipCode);
        dto.setAddress(address);
        return dto;
    }

    public static Address toDomain(AddressDto dto) {
        var domain = new Address();
        domain.setId(dto.getId());
        domain.country = Country.toDomain(dto.getCountryDto());
        domain.setCity(dto.getCity());
        domain.setName(dto.getName());
        domain.setSector(dto.getSector());
        domain.setZipCode(dto.getZipCode());
        domain.setAddress(dto.getAddress());
        return domain;
    }
}
