package com.cc.service.dto;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cc.domain.EnumEducation} entity.
 */
@ApiModel(description = "学历")
public class EnumEducationDTO implements Serializable {

    private Long id;

    private String valuez;

    private Integer orderz;

    private String tenentCode;


    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValuez() {
        return valuez;
    }

    public void setValuez(String valuez) {
        this.valuez = valuez;
    }

    public Integer getOrderz() {
        return orderz;
    }

    public void setOrderz(Integer orderz) {
        this.orderz = orderz;
    }

    public String getTenentCode() {
        return tenentCode;
    }

    public void setTenentCode(String tenentCode) {
        this.tenentCode = tenentCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long enumEducationId) {
        this.parentId = enumEducationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnumEducationDTO enumEducationDTO = (EnumEducationDTO) o;
        if (enumEducationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), enumEducationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnumEducationDTO{" +
            "id=" + getId() +
            ", valuez='" + getValuez() + "'" +
            ", orderz=" + getOrderz() +
            ", tenentCode='" + getTenentCode() + "'" +
            ", parent=" + getParentId() +
            "}";
    }
}
