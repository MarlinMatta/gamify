package edu.uapa.web.app.gamify.domains.securities;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.utesa.lib.models.dtos.security.ParamDto;
import edu.utesa.lib.models.enums.type.security.EnumParamValueType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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

    public Parameter() {
    }

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public EnumParamValueType getParamValueType() {
        return paramValueType;
    }

    public void setParamValueType(EnumParamValueType paramValueType) {
        this.paramValueType = paramValueType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getChangeRoot() {
        return changeRoot;
    }

    public void setChangeRoot(Boolean changeRoot) {
        this.changeRoot = changeRoot;
    }
}
