package edu.uapa.web.app.gamify.domains.locations;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "countries")
public class Country extends Auditable {
    @Column(nullable = false)
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
