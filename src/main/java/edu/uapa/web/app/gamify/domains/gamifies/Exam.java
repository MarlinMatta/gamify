package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.domains.schools.Subject;
import edu.uapa.web.app.gamify.domains.schools.Teacher;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.ExamDto;
import edu.utesa.lib.models.dtos.school.ProblemDto;
import edu.utesa.lib.models.enums.GameDifficulty;
import edu.utesa.lib.models.enums.GameDifficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "exams")
public class Exam extends Auditable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @Enumerated(EnumType.ORDINAL)
    private GameDifficulty gameDifficulty;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Column(nullable = false)
    private int problemQuantity;
    @Column(nullable = false)
    private Date fromDate;
    @Column(nullable = false)
    private Date toDate;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "exam_problems", joinColumns = {@JoinColumn(name = "exam_id")}, inverseJoinColumns = @JoinColumn(name = "problem_id"))
    private Set<Problem> problems;
    @Column(nullable = false)
    private int points;

    public ExamDto toLazyDto() {
        ExamDto dto = new ExamDto();
        dto.setId(getId());
        dto.setTeacherDto(teacher.toLazyDto());
        dto.setGameDifficulty(gameDifficulty);
        dto.setSubjectDto(subject.toLazyDto());
        dto.setTopicDto(topic.toLazyDto());
        dto.setProblemQuantity(problemQuantity);
        dto.setFromDate(fromDate.toString());
        dto.setToDate(toDate.toString());
        dto.setProblems(problems.stream().map(Problem::toLazyDto).collect(Collectors.toSet()));
        dto.setPoints(points);
        return dto;
    }

    public static Exam toDomain(ExamDto dto) {
        var domain = new Exam();
        domain.setId(dto.getId());
        domain.teacher = Teacher.toDomain(dto.getTeacherDto());
        domain.setGameDifficulty(dto.getGameDifficulty());
        domain.subject = Subject.toDomain(dto.getSubjectDto());
        domain.topic = Topic.toDomain(dto.getTopicDto());
        domain.setProblemQuantity(dto.getProblemQuantity());
        try {
            domain.setFromDate(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getFromDate()));
            domain.setToDate(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getToDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Problem> problem = new HashSet<>();
        dto.getProblems().forEach(problemDto -> problem.add(Problem.toDomain(problemDto)));
        domain.setProblems(problem);
        domain.setPoints(dto.getPoints());
        return domain;
    }
}
