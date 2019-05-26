package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.domains.schools.Student;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.uapa.web.app.gamify.models.enums.Difficulty;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity(name = "studenAchievements")
public class StudenAchievement extends Auditable {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentAchievement_id")
    private StudenAchievement studenAchievement;

    public StudenAchievement() {
    }

    public StudenAchievement(Student student, StudenAchievement studenAchievement) {
        this.student = student;
        this.studenAchievement = studenAchievement;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudenAchievement getStudenAchievement() {
        return studenAchievement;
    }

    public void setStudenAchievement(StudenAchievement studenAchievement) {
        this.studenAchievement = studenAchievement;
    }
}


