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
@Entity(name = "solutions")
public class Solution extends Auditable {
    @Column(nullable = false)
    private String solution;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column(nullable = false)
    private boolean is_correct;
}
