package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.domains.schools.Teacher;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.ProblemDto;
import edu.utesa.lib.models.enums.ExamDifficulty;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Enumerated(EnumType.ORDINAL)
    private ExamDifficulty examDifficulty;
    @Column(nullable = false)
    private String question;
    private Double point;
    @Column(nullable = false)
    private String correctAnswer;
    @Column(nullable = false)
    private String incorrectAnswer01;
    @Column(nullable = false)
    private String incorrectAnswer02;
    @Column(nullable = false)
    private String incorrectAnswer03;


    public ProblemDto toLazyDto() {
        ProblemDto dto = new ProblemDto();
        dto.setId(getId());
        dto.setTeacherDto(teacher.toLazyDto());
        dto.setTopicDto(topic.toLazyDto());
        dto.setExamDifficulty(examDifficulty);
        dto.setQuestion(question);
        dto.setPoint(point);
        dto.setCorrectAnswer(correctAnswer);
        dto.setIncorrectAnswer01(incorrectAnswer01);
        dto.setIncorrectAnswer02(incorrectAnswer02);
        dto.setIncorrectAnswer03(incorrectAnswer03);
        return dto;
    }

    public static Problem toDomain(ProblemDto dto) {
        var domain = new Problem();
        domain.setId(dto.getId());
        domain.teacher = Teacher.toDomain(dto.getTeacherDto());
        domain.topic = Topic.toDomain(dto.getTopicDto());
        domain.setExamDifficulty(dto.getExamDifficulty());
        domain.setQuestion(dto.getQuestion());
        domain.setPoint(dto.getPoint());
        domain.setCorrectAnswer(dto.getCorrectAnswer());
        domain.setIncorrectAnswer01(dto.getIncorrectAnswer01());
        domain.setIncorrectAnswer02(dto.getIncorrectAnswer02());
        domain.setIncorrectAnswer03(dto.getIncorrectAnswer03());
        return domain;
    }
}
