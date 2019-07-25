package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.domains.schools.Student;
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
@Entity(name = "studentAchievements")
public class StudentAchievement extends Auditable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentAchievement_id")
    private StudentAchievement studentAchievement;
}


