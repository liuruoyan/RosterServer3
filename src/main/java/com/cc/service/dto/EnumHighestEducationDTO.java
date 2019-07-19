package com.cc.service.dto;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cc.domain.EnumHighestEducation} entity.
 */
@ApiModel(description = "最高学历")
public class EnumHighestEducationDTO implements Serializable {

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

    public void setParentId(Long enumHighestEducationId) {
        this.parentId = enumHighestEducationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnumHighestEducationDTO enumHighestEducationDTO = (EnumHighestEducationDTO) o;
        if (enumHighestEducationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), enumHighestEducationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnumHighestEducationDTO{" +
            "id=" + getId() +
            ", valuez='" + getValuez() + "'" +
            ", orderz=" + getOrderz() +
            ", tenentCode='" + getTenentCode() + "'" +
            ", parent=" + getParentId() +
            "}";
    }
}
