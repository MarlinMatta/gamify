package edu.uapa.web.app.gamify.domains.people;

import edu.uapa.web.app.gamify.domains.locations.Address;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.enums.person.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "people")
public class Person extends Auditable {
    private String names;
    private String lastNames;
    private Date birth;
    private Gender gender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
}

