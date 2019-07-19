package com.cc.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cc.domain.DirectSupervisor} entity.
 */
@ApiModel(description = "直属领导信息 (多对一 员工)")
public class DirectSupervisorDTO implements Serializable {

    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value = "员工姓名")
    private String name;

    /**
     * 员工手机号
     */
    @ApiModelProperty(value = "员工手机号")
    private String phone;

    /**
     * 行政领导姓名
     */
    @ApiModelProperty(value = "行政领导姓名")
    private String aSupName;

    /**
     * 行政领导手机
     */
    @ApiModelProperty(value = "行政领导手机")
    private String aSupPhone;

    /**
     * 业务领导姓名
     */
    @ApiModelProperty(value = "业务领导姓名")
    private String bSupName;

    /**
     * 业务领导手机
     */
    @ApiModelProperty(value = "业务领导手机")
    private String bSupPhone;

    /**
     * 财务领导姓名
     */
    @ApiModelProperty(value = "财务领导姓名")
    private String fSubName;

    /**
     * 财务领导手机
     */
    @ApiModelProperty(value = "财务领导手机")
    private String fSubPhone;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getaSupName() {
        return aSupName;
    }

    public void setaSupName(String aSupName) {
        this.aSupName = aSupName;
    }

    public String getaSupPhone() {
        return aSupPhone;
    }

    public void setaSupPhone(String aSupPhone) {
        this.aSupPhone = aSupPhone;
    }

    public String getbSupName() {
        return bSupName;
    }

    public void setbSupName(String bSupName) {
        this.bSupName = bSupName;
    }

    public String getbSupPhone() {
        return bSupPhone;
    }

    public void setbSupPhone(String bSupPhone) {
        this.bSupPhone = bSupPhone;
    }

    public String getfSubName() {
        return fSubName;
    }

    public void setfSubName(String fSubName) {
        this.fSubName = fSubName;
    }

    public String getfSubPhone() {
        return fSubPhone;
    }

    public void setfSubPhone(String fSubPhone) {
        this.fSubPhone = fSubPhone;
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

        DirectSupervisorDTO directSupervisorDTO = (DirectSupervisorDTO) o;
        if (directSupervisorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), directSupervisorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DirectSupervisorDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", aSupName='" + getaSupName() + "'" +
            ", aSupPhone='" + getaSupPhone() + "'" +
            ", bSupName='" + getbSupName() + "'" +
            ", bSupPhone='" + getbSupPhone() + "'" +
            ", fSubName='" + getfSubName() + "'" +
            ", fSubPhone='" + getfSubPhone() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            ", emp=" + getEmpId() +
            "}";
    }
}
