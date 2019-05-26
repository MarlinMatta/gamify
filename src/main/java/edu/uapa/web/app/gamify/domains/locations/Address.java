package edu.uapa.web.app.gamify.domains.locations;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;

import javax.persistence.*;

@Entity(name = "addresses")
public class Address extends Auditable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String sector;

    private String address;

    public Address() {
    }

    public Address(Country country, String city, String sector, String address) {
        this.country = country;
        this.city = city;
        this.sector = sector;
        this.address = address;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
