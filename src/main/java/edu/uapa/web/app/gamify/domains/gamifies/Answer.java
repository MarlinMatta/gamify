package edu.uapa.web.app.gamify.domains.gamifies;

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
@Entity(name = "answers")
public class Answer extends Auditable {
    @Column(nullable = false)
    String description;
}
