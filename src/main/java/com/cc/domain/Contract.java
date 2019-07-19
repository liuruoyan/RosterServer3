package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 合同（ 多对一 员工 ）
 */
@Entity
@Table(name = "contract")
public class Contract implements Serializable {

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
     * 当前合同起始日
     */
    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * 当前合同终止日
     */
    @Column(name = "end_date")
    private LocalDate endDate;

    /**
     * 工作邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 工作电话
     */
    @Column(name = "work_tel")
    private String workTel;

    /**
     * 试用期到期日
     */
    @Column(name = "probation_end_day")
    private LocalDate probationEndDay;

    /**
     * 试用期长度
     */
    @Column(name = "probation_length")
    private Integer probationLength;

    /**
     * 添加字段
     */
    @Column(name = "other")
    private String other;

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
     * 合同类型
     */
    @ManyToOne
    @JsonIgnoreProperties("contracts")
    private EnumContractType contractType;

    @ManyToOne
    @JsonIgnoreProperties("contracts")
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

    public Contract code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Contract startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Contract endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getEmail() {
        return email;
    }

    public Contract email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkTel() {
        return workTel;
    }

    public Contract workTel(String workTel) {
        this.workTel = workTel;
        return this;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public LocalDate getProbationEndDay() {
        return probationEndDay;
    }

    public Contract probationEndDay(LocalDate probationEndDay) {
        this.probationEndDay = probationEndDay;
        return this;
    }

    public void setProbationEndDay(LocalDate probationEndDay) {
        this.probationEndDay = probationEndDay;
    }

    public Integer getProbationLength() {
        return probationLength;
    }

    public Contract probationLength(Integer probationLength) {
        this.probationLength = probationLength;
        return this;
    }

    public void setProbationLength(Integer probationLength) {
        this.probationLength = probationLength;
    }

    public String getOther() {
        return other;
    }

    public Contract other(String other) {
        this.other = other;
        return this;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public Contract isSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
        return this;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public Contract isHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
        return this;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public EnumContractType getContractType() {
        return contractType;
    }

    public Contract contractType(EnumContractType enumContractType) {
        this.contractType = enumContractType;
        return this;
    }

    public void setContractType(EnumContractType enumContractType) {
        this.contractType = enumContractType;
    }

    public Employee getEmp() {
        return emp;
    }

    public Contract emp(Employee employee) {
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
        if (!(o instanceof Contract)) {
            return false;
        }
        return id != null && id.equals(((Contract) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Contract{" +
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
            "}";
    }
}
