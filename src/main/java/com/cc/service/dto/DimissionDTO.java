package com.cc.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cc.domain.Dimission} entity.
 */
@ApiModel(description = "离职信息 (多对一 员工)")
public class DimissionDTO implements Serializable {

    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 最后工作日
     */
    @ApiModelProperty(value = "最后工作日")
    private LocalDate lastDate;

    /**
     * 离职原因
     */
    @ApiModelProperty(value = "离职原因")
    private String reason;

    /**
     * 员工是否验证
     */
    @ApiModelProperty(value = "员工是否验证")
    private Boolean isSelfVerify;

    /**
     * 管理（hr）是否验证
     */
    @ApiModelProperty(value = "管理（hr）是否验证")
    private Boolean isHrVerify;

    /**
     * 离职类型
     */
    @ApiModelProperty(value = "离职类型")

    private Long dimissionTypeId;

    private Long empId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public Long getDimissionTypeId() {
        return dimissionTypeId;
    }

    public void setDimissionTypeId(Long enumDimissionTypeId) {
        this.dimissionTypeId = enumDimissionTypeId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long employeeId) {
        this.empId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DimissionDTO dimissionDTO = (DimissionDTO) o;
        if (dimissionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dimissionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DimissionDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", lastDate='" + getLastDate() + "'" +
            ", reason='" + getReason() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            ", dimissionType=" + getDimissionTypeId() +
            ", emp=" + getEmpId() +
            "}";
    }
}
