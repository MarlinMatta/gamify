package edu.uapa.web.app.gamify.domains.company;

import edu.uapa.web.app.gamify.domains.securities.Parameter;
import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "companies")
public class Company extends Auditable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private String url;
    private String activity;
    private String imgAddress;
    private String description;
    private String rnc;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "company_parameters", joinColumns = {@JoinColumn(name = "company_id")}, inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private Set<Parameter> parameters;
}
