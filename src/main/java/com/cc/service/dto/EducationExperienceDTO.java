package com.cc.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cc.domain.EducationExperience} entity.
 */
@ApiModel(description = "教育经历(多对一 员工)")
public class EducationExperienceDTO implements Serializable {

    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**
     * 学校
     */
    @ApiModelProperty(value = "学校")
    private String school;

    /**
     * 专业
     */
    @ApiModelProperty(value = "专业")
    private String major;

    /**
     * 入校时间
     */
    @ApiModelProperty(value = "入校时间")
    private LocalDate inDate;

    /**
     * 结业时间
     */
    @ApiModelProperty(value = "结业时间")
    private LocalDate graduationDate;

    /**
     * 是否取得学位
     */
    @ApiModelProperty(value = "是否取得学位")
    private Boolean inception;

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
     * 学历
     */
    @ApiModelProperty(value = "学历")

    private Long educationId;

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public Boolean isInception() {
        return inception;
    }

    public void setInception(Boolean inception) {
        this.inception = inception;
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

    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long enumEducationId) {
        this.educationId = enumEducationId;
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

        EducationExperienceDTO educationExperienceDTO = (EducationExperienceDTO) o;
        if (educationExperienceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationExperienceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationExperienceDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", school='" + getSchool() + "'" +
            ", major='" + getMajor() + "'" +
            ", inDate='" + getInDate() + "'" +
            ", graduationDate='" + getGraduationDate() + "'" +
            ", inception='" + isInception() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            ", education=" + getEducationId() +
            ", emp=" + getEmpId() +
            "}";
    }
}
