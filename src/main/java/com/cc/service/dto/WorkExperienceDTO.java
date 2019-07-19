package com.cc.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cc.domain.WorkExperience} entity.
 */
@ApiModel(description = "工作经历（多对一 员工）")
public class WorkExperienceDTO implements Serializable {

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
    private String eName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phoneNum;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String company;

    /**
     * 担任职位
     */
    @ApiModelProperty(value = "担任职位")
    private String job;

    /**
     * 职位描述
     */
    @ApiModelProperty(value = "职位描述")
    private String jobDesc;

    /**
     * 入职日期
     */
    @ApiModelProperty(value = "入职日期")
    private LocalDate hireDate;

    /**
     * 离职日期
     */
    @ApiModelProperty(value = "离职日期")
    private LocalDate leaveDate;

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

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
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

        WorkExperienceDTO workExperienceDTO = (WorkExperienceDTO) o;
        if (workExperienceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workExperienceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkExperienceDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", eName='" + geteName() + "'" +
            ", phoneNum='" + getPhoneNum() + "'" +
            ", company='" + getCompany() + "'" +
            ", job='" + getJob() + "'" +
            ", jobDesc='" + getJobDesc() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", leaveDate='" + getLeaveDate() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            ", emp=" + getEmpId() +
            "}";
    }
}
