package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 离职信息 (多对一 员工)
 */
@Entity
@Table(name = "dimission")
public class Dimission implements Serializable {

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
     * 最后工作日
     */
    @Column(name = "last_date")
    private LocalDate lastDate;

    /**
     * 离职原因
     */
    @Column(name = "reason")
    private String reason;

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
     * 离职类型
     */
    @ManyToOne
    @JsonIgnoreProperties("dimissions")
    private EnumDimissionType dimissionType;

    @ManyToOne
    @JsonIgnoreProperties("dimissions")
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

    public Dimission code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public Dimission lastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
        return this;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public String getReason() {
        return reason;
    }

    public Dimission reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public Dimission isSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
        return this;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public Dimission isHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
        return this;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public EnumDimissionType getDimissionType() {
        return dimissionType;
    }

    public Dimission dimissionType(EnumDimissionType enumDimissionType) {
        this.dimissionType = enumDimissionType;
        return this;
    }

    public void setDimissionType(EnumDimissionType enumDimissionType) {
        this.dimissionType = enumDimissionType;
    }

    public Employee getEmp() {
        return emp;
    }

    public Dimission emp(Employee employee) {
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
        if (!(o instanceof Dimission)) {
            return false;
        }
        return id != null && id.equals(((Dimission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dimission{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", lastDate='" + getLastDate() + "'" +
            ", reason='" + getReason() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            "}";
    }
}
