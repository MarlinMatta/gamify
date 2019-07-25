package edu.uapa.web.app.gamify.domains.locations;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
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
}
