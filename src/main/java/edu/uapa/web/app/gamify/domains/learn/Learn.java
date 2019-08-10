package edu.uapa.web.app.gamify.domains.learn;

import edu.uapa.web.app.gamify.domains.gamifies.Topic;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.LearnDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "learn")
public class Learn extends Auditable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Column(nullable = false)
    @Lob
    private String description;

    public LearnDto toLazyDto() {
        var dto = new LearnDto();
        dto.setId(getId());
        dto.setTopicDto(topic.toLazyDto());
        dto.setDescription(description);
        return dto;
    }

    public static Learn toDomain(LearnDto dto) {
        var domain = new Learn();
        domain.setId(dto.getId());
        domain.setTopic(Topic.toDomain(dto.getTopicDto()));
        domain.setDescription(dto.getDescription());
        return domain;
    }
}
