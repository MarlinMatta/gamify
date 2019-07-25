package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.domains.schools.Teacher;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.uapa.web.app.gamify.models.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "problems")
public class Problem extends Auditable {

    @Column(nullable = false)
    private String problem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private String imageUrl;
}
