package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 公司内兼任信息  (多对一 员工)
 */
@Entity
@Table(name = "additional_post")
public class AdditionalPost implements Serializable {

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
    @Column(name = "name")
    private String name;

    /**
     * 员工手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 兼任部门
     */
    @Column(name = "dept")
    private String dept;

    /**
     * 兼任职位
     */
    @Column(name = "job")
    private String job;

    /**
     * 开始日期
     */
    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * 结束日期
     */
    @Column(name = "end_date")
    private LocalDate endDate;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

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
    @JsonIgnoreProperties("additionalPosts")
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

    public AdditionalPost code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public AdditionalPost name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public AdditionalPost phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDept() {
        return dept;
    }

    public AdditionalPost dept(String dept) {
        this.dept = dept;
        return this;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getJob() {
        return job;
    }

    public AdditionalPost job(String job) {
        this.job = job;
        return this;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public AdditionalPost startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public AdditionalPost endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public AdditionalPost remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public AdditionalPost isSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
        return this;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public AdditionalPost isHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
        return this;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public Employee getEmp() {
        return emp;
    }

    public AdditionalPost emp(Employee employee) {
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
        if (!(o instanceof AdditionalPost)) {
            return false;
        }
        return id != null && id.equals(((AdditionalPost) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdditionalPost{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", dept='" + getDept() + "'" +
            ", job='" + getJob() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", remark='" + getRemark() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            "}";
    }
}
