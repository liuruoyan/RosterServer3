package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 直属领导信息 (多对一 员工)
 */
@Entity
@Table(name = "direct_supervisor")
public class DirectSupervisor implements Serializable {

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
     * 行政领导姓名
     */
    @Column(name = "a_sup_name")
    private String aSupName;

    /**
     * 行政领导手机
     */
    @Column(name = "a_sup_phone")
    private String aSupPhone;

    /**
     * 业务领导姓名
     */
    @Column(name = "b_sup_name")
    private String bSupName;

    /**
     * 业务领导手机
     */
    @Column(name = "b_sup_phone")
    private String bSupPhone;

    /**
     * 财务领导姓名
     */
    @Column(name = "f_sub_name")
    private String fSubName;

    /**
     * 财务领导手机
     */
    @Column(name = "f_sub_phone")
    private String fSubPhone;

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
    @JsonIgnoreProperties("directSupervisors")
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

    public DirectSupervisor code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public DirectSupervisor name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public DirectSupervisor phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getaSupName() {
        return aSupName;
    }

    public DirectSupervisor aSupName(String aSupName) {
        this.aSupName = aSupName;
        return this;
    }

    public void setaSupName(String aSupName) {
        this.aSupName = aSupName;
    }

    public String getaSupPhone() {
        return aSupPhone;
    }

    public DirectSupervisor aSupPhone(String aSupPhone) {
        this.aSupPhone = aSupPhone;
        return this;
    }

    public void setaSupPhone(String aSupPhone) {
        this.aSupPhone = aSupPhone;
    }

    public String getbSupName() {
        return bSupName;
    }

    public DirectSupervisor bSupName(String bSupName) {
        this.bSupName = bSupName;
        return this;
    }

    public void setbSupName(String bSupName) {
        this.bSupName = bSupName;
    }

    public String getbSupPhone() {
        return bSupPhone;
    }

    public DirectSupervisor bSupPhone(String bSupPhone) {
        this.bSupPhone = bSupPhone;
        return this;
    }

    public void setbSupPhone(String bSupPhone) {
        this.bSupPhone = bSupPhone;
    }

    public String getfSubName() {
        return fSubName;
    }

    public DirectSupervisor fSubName(String fSubName) {
        this.fSubName = fSubName;
        return this;
    }

    public void setfSubName(String fSubName) {
        this.fSubName = fSubName;
    }

    public String getfSubPhone() {
        return fSubPhone;
    }

    public DirectSupervisor fSubPhone(String fSubPhone) {
        this.fSubPhone = fSubPhone;
        return this;
    }

    public void setfSubPhone(String fSubPhone) {
        this.fSubPhone = fSubPhone;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public DirectSupervisor isSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
        return this;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public DirectSupervisor isHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
        return this;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public Employee getEmp() {
        return emp;
    }

    public DirectSupervisor emp(Employee employee) {
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
        if (!(o instanceof DirectSupervisor)) {
            return false;
        }
        return id != null && id.equals(((DirectSupervisor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DirectSupervisor{" +
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
            "}";
    }
}
