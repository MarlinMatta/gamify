package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "topics")
public class Topic extends Auditable {

    @Column(nullable = false)
    private String topicName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String example;
}
