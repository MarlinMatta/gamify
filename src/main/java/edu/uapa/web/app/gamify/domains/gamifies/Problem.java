package edu.uapa.web.app.gamify.domains.gamifies;

import edu.utesa.lib.models.dtos.school.AnswerDto;
import edu.utesa.lib.models.dtos.school.TeacherDto;
import edu.utesa.lib.models.dtos.school.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "problems")
public class Problem {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeacherDto teacherDto;
    private TopicDto topicDto;
    private String question;
    private List<AnswerDto> incorrectAnswers;
    private AnswerDto correctAnswer;
}
