package edu.uapa.web.app.gamify.domains.schools;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.TopicDto;
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @Column(nullable = false)
    private String name;
    private String description;

    public TopicDto toLazyDto() {
        TopicDto dto = new TopicDto();
        dto.setId(getId());
        dto.setSubjectDto(subject.toLazyDto());
        dto.setName(name);
        dto.setDescription(description);
        return dto;
    }

    public static Topic toDomain(TopicDto dto) {
        var domain = new Topic();
        domain.setId(dto.getId());
        domain.subject = Subject.toDomain(dto.getSubjectDto());
        domain.setName(dto.getName());
        domain.setDescription(dto.getDescription());
        return domain;
    }
}


