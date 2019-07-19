package com.cc.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cc.domain.Contract} entity.
 */
@ApiModel(description = "合同（ 多对一 员工 ）")
public class ContractDTO implements Serializable {

    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 当前合同起始日
     */
    @ApiModelProperty(value = "当前合同起始日")
    private LocalDate startDate;

    /**
     * 当前合同终止日
     */
    @ApiModelProperty(value = "当前合同终止日")
    private LocalDate endDate;

    /**
     * 工作邮箱
     */
    @ApiModelProperty(value = "工作邮箱")
    private String email;

    /**
     * 工作电话
     */
    @ApiModelProperty(value = "工作电话")
    private String workTel;

    /**
     * 试用期到期日
     */
    @ApiModelProperty(value = "试用期到期日")
    private LocalDate probationEndDay;

    /**
     * 试用期长度
     */
    @ApiModelProperty(value = "试用期长度")
    private Integer probationLength;

    /**
     * 添加字段
     */
    @ApiModelProperty(value = "添加字段")
    private String other;

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
     * 合同类型
     */
    @ApiModelProperty(value = "合同类型")

    private Long contractTypeId;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public LocalDate getProbationEndDay() {
        return probationEndDay;
    }

    public void setProbationEndDay(LocalDate probationEndDay) {
        this.probationEndDay = probationEndDay;
    }

    public Integer getProbationLength() {
        return probationLength;
    }

    public void setProbationLength(Integer probationLength) {
        this.probationLength = probationLength;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

    public Long getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(Long enumContractTypeId) {
        this.contractTypeId = enumContractTypeId;
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

        ContractDTO contractDTO = (ContractDTO) o;
        if (contractDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contractDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContractDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", email='" + getEmail() + "'" +
            ", workTel='" + getWorkTel() + "'" +
            ", probationEndDay='" + getProbationEndDay() + "'" +
            ", probationLength=" + getProbationLength() +
            ", other='" + getOther() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            ", contractType=" + getContractTypeId() +
            ", emp=" + getEmpId() +
            "}";
    }
}
