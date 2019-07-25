package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.domains.locations.Address;
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
@Entity(name = "schools")
public class School extends Auditable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String district;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    public School toDto() {
        School dto = new School();
        dto.setId(getId());
        dto.setName(name);
        dto.setDistrict(district);
        dto.setAddress(address);
        return dto;
    }

    public static School toDomain(School dto) {
        var school = new School();
        school.setId(dto.getId());
        school.setName(dto.getName());
        school.setDistrict(dto.getDistrict());
        school.setAddress(dto.getAddress());
        return school;
    }
}
