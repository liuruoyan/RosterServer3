package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 工作经历（多对一 员工）
 */
@Entity
@Table(name = "work_experience")
public class WorkExperience implements Serializable {

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
     * 员工姓名
     */
    @Column(name = "e_name")
    private String eName;

    /**
     * 手机号码
     */
    @Column(name = "phone_num")
    private String phoneNum;

    /**
     * 公司名称
     */
    @Column(name = "company")
    private String company;

    /**
     * 担任职位
     */
    @Column(name = "job")
    private String job;

    /**
     * 职位描述
     */
    @Column(name = "job_desc")
    private String jobDesc;

    /**
     * 入职日期
     */
    @Column(name = "hire_date")
    private LocalDate hireDate;

    /**
     * 离职日期
     */
    @Column(name = "leave_date")
    private LocalDate leaveDate;

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

    @ManyToOne
    @JsonIgnoreProperties("workExperiences")
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

    public WorkExperience code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String geteName() {
        return eName;
    }

    public WorkExperience eName(String eName) {
        this.eName = eName;
        return this;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public WorkExperience phoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCompany() {
        return company;
    }

    public WorkExperience company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public WorkExperience job(String job) {
        this.job = job;
        return this;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public WorkExperience jobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
        return this;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public WorkExperience hireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public WorkExperience leaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
        return this;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public WorkExperience isSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
        return this;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public WorkExperience isHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
        return this;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public Employee getEmp() {
        return emp;
    }

    public WorkExperience emp(Employee employee) {
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
        if (!(o instanceof WorkExperience)) {
            return false;
        }
        return id != null && id.equals(((WorkExperience) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WorkExperience{" +
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
            "}";
    }
}
