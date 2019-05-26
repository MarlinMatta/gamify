package edu.uapa.web.app.gamify.domains.people;

import edu.uapa.web.app.gamify.domains.locations.Address;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.uapa.web.app.gamify.models.enums.Gender;

import javax.persistence.*;
import java.util.Date;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    public Person() {
    }

    public Person(String names, String lastNames, Date birth, Gender gender, Address address) {
        this.names = names;
        this.lastNames = lastNames;
        this.birth = birth;
        this.gender = gender;
        this.address = address;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

