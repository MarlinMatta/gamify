package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.school.AnswerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "answers")
public class Answer extends Auditable {
    @Column(nullable = false)
    String description;

    public AnswerDto toLazyDto() {
        AnswerDto dto = new AnswerDto();
        dto.setId(getId());
        dto.setDescription(description);
        return dto;
    }

    public static Answer toDomain(AnswerDto dto) {
        var domain = new Answer();
        domain.setId(dto.getId());
        domain.setDescription(dto.getDescription());
        return domain;
    }
}
