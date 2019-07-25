package edu.uapa.web.app.gamify.domains.securities;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.security.ParamDto;
import edu.utesa.lib.models.enums.type.security.EnumParamValueType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "parameters")
public class Parameter extends Auditable {
    @Column(unique = true)
    private Integer code;
    @Enumerated(EnumType.ORDINAL)
    private EnumParamValueType paramValueType;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String value = "";
    private String description;
    private Boolean changeRoot = false;

    public ParamDto toDto() {
        ParamDto dto = new ParamDto();
        dto.setId(getId());
        dto.setCode(code);
        dto.setName(name);
        dto.setType(paramValueType);
        dto.setValue(value);
        dto.setChangeRoot(changeRoot);
        dto.setDescription(description);
        return dto;
    }

    public static Parameter toDomain(ParamDto dto) {
        var parameter = new Parameter();
        parameter.setId(dto.getId());
        parameter.setCode(dto.getCode());
        parameter.setName(dto.getName());
        parameter.setParamValueType(dto.getType());
        parameter.setValue(dto.getValue());
        parameter.setChangeRoot(dto.getChangeRoot());
        parameter.setDescription(dto.getDescription());
        return parameter;
    }
}
