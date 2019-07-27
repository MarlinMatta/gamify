package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.uapa.web.app.gamify.models.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DecimalFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "achievements")
public class Achievement extends Auditable {

    @Column(nullable = false)
    private String difficultyName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private GameTopic gameTopic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    private String description;

    private DecimalFormat quantity;

    private DecimalFormat percent;

    private String iconUrl;
}
