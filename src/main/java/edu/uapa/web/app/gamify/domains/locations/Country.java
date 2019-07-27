package edu.uapa.web.app.gamify.domains.locations;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.location.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "countries")
public class Country extends Auditable {
    @Column(nullable = false)
    private String name;

    public CountryDto toLazyDto() {
        CountryDto dto = new CountryDto();
        dto.setId(getId());
        dto.setName(name);
        return dto;
    }

    public static Country toDomain(CountryDto dto) {
        var country = new Country();
        country.setId(dto.getId());
        country.setName(dto.getName());
        return country;
    }
}
