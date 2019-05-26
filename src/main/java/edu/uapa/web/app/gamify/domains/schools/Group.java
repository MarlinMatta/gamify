package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.domains.people.Person;
import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;

import javax.persistence.*;

@Entity(name = "groups")
public class Group extends Auditable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    @Column (nullable = false)
    private Person person;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lazy_id")
    @Column(nullable = false)
    private User user;

    public Group() {
    }

    public Group(Person person, User user) {
        this.person = person;
        this.user = user;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


