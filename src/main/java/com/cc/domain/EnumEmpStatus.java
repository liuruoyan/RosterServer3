package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 员工状态
 */
@Entity
@Table(name = "enum_emp_status")
public class EnumEmpStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valuez")
    private String valuez;

    @Column(name = "orderz")
    private Integer orderz;

    @Column(name = "tenent_code")
    private String tenentCode;

    @ManyToOne
    @JsonIgnoreProperties("enumEmpStatuses")
    private EnumEmpStatus parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValuez() {
        return valuez;
    }

    public EnumEmpStatus valuez(String valuez) {
        this.valuez = valuez;
        return this;
    }

    public void setValuez(String valuez) {
        this.valuez = valuez;
    }

    public Integer getOrderz() {
        return orderz;
    }

    public EnumEmpStatus orderz(Integer orderz) {
        this.orderz = orderz;
        return this;
    }

    public void setOrderz(Integer orderz) {
        this.orderz = orderz;
    }

    public String getTenentCode() {
        return tenentCode;
    }

    public EnumEmpStatus tenentCode(String tenentCode) {
        this.tenentCode = tenentCode;
        return this;
    }

    public void setTenentCode(String tenentCode) {
        this.tenentCode = tenentCode;
    }

    public EnumEmpStatus getParent() {
        return parent;
    }

    public EnumEmpStatus parent(EnumEmpStatus enumEmpStatus) {
        this.parent = enumEmpStatus;
        return this;
    }

    public void setParent(EnumEmpStatus enumEmpStatus) {
        this.parent = enumEmpStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EnumEmpStatus)) {
            return false;
        }
        return id != null && id.equals(((EnumEmpStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EnumEmpStatus{" +
            "id=" + getId() +
            ", valuez='" + getValuez() + "'" +
            ", orderz=" + getOrderz() +
            ", tenentCode='" + getTenentCode() + "'" +
            "}";
    }
}
