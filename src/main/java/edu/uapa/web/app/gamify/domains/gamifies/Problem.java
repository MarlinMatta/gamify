package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.domains.schools.Teacher;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.ProblemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "problems")
public class Problem extends Auditable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Column(nullable = false)
    private String question;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "problem_answers", joinColumns = {@JoinColumn(name = "problem_id")}, inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private Set<Answer> incorrectAnswers;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer correctAnswer;

    public ProblemDto toLazyDto() {
        ProblemDto dto = new ProblemDto();
        dto.setId(getId());
        dto.setTeacherDto (teacher.toLazyDto());
        dto.setTopicDto(topic.toLazyDto());
        dto.setQuestion(question);
        dto.setIncorrectAnswers(incorrectAnswers.stream().map(Answer::toLazyDto).collect(Collectors.toSet()));
        dto.setCorrectAnswer(correctAnswer.toLazyDto());
        return dto;
    }

    public static Problem toDomain(ProblemDto dto) {
        var domain = new Problem();
        domain.setId(dto.getId());
        domain.teacher = Teacher.toDomain(dto.getTeacherDto());
        domain.topic = Topic.toDomain(dto.getTopicDto());
        domain.setQuestion(dto.getQuestion());
        domain.setIncorrectAnswers(dto.getIncorrectAnswers().stream().map(Answer::toDomain).collect(Collectors.toSet()));
        domain.correctAnswer = Answer.toDomain(dto.getCorrectAnswer());
        return domain;
    }
}
