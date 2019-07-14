package edu.uapa.web.app.gamify.domains.company;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "departments")
public class Department extends Auditable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    private Branch branch;
    private String name;
}
