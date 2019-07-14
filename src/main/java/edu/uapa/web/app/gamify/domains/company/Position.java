package edu.uapa.web.app.gamify.domains.company;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import lombok.*;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "positions")
public class Position extends Auditable {
    private String name;
    private String description;
}