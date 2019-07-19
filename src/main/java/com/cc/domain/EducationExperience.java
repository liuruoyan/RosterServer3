package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 教育经历(多对一 员工)
 */
@Entity
@Table(name = "education_experience")
public class EducationExperience implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 编码
     */
    @Column(name = "code")
    private String code;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 手机号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 学校
     */
    @Column(name = "school")
    private String school;

    /**
     * 专业
     */
    @Column(name = "major")
    private String major;

    /**
     * 入校时间
     */
    @Column(name = "in_date")
    private LocalDate inDate;

    /**
     * 结业时间
     */
    @Column(name = "graduation_date")
    private LocalDate graduationDate;

    /**
     * 是否取得学位
     */
    @Column(name = "inception")
    private Boolean inception;

    /**
     * 员工是否验证
     */
    @Column(name = "is_self_verify")
    private Boolean isSelfVerify;

    /**
     * 管理（hr）是否验证
     */
    @Column(name = "is_hr_verify")
    private Boolean isHrVerify;

    /**
     * 学历
     */
    @ManyToOne
    @JsonIgnoreProperties("educationExperiences")
    private EnumEducation education;

    @ManyToOne
    @JsonIgnoreProperties("educationExperiences")
    private Employee emp;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public EducationExperience code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public EducationExperience name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public EducationExperience phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchool() {
        return school;
    }

    public EducationExperience school(String school) {
        this.school = school;
        return this;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public EducationExperience major(String major) {
        this.major = major;
        return this;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public EducationExperience inDate(LocalDate inDate) {
        this.inDate = inDate;
        return this;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public EducationExperience graduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
        return this;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public Boolean isInception() {
        return inception;
    }

    public EducationExperience inception(Boolean inception) {
        this.inception = inception;
        return this;
    }

    public void setInception(Boolean inception) {
        this.inception = inception;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public EducationExperience isSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
        return this;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public EducationExperience isHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
        return this;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public EnumEducation getEducation() {
        return education;
    }

    public EducationExperience education(EnumEducation enumEducation) {
        this.education = enumEducation;
        return this;
    }

    public void setEducation(EnumEducation enumEducation) {
        this.education = enumEducation;
    }

    public Employee getEmp() {
        return emp;
    }

    public EducationExperience emp(Employee employee) {
        this.emp = employee;
        return this;
    }

    public void setEmp(Employee employee) {
        this.emp = employee;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EducationExperience)) {
            return false;
        }
        return id != null && id.equals(((EducationExperience) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EducationExperience{" +
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
            "}";
    }
}
