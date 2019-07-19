package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 社保状态
 */
@Entity
@Table(name = "enum_ss_status")
public class EnumSsStatus implements Serializable {

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
    @JsonIgnoreProperties("enumSsStatuses")
    private EnumSsStatus parent;

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

    public EnumSsStatus valuez(String valuez) {
        this.valuez = valuez;
        return this;
    }

    public void setValuez(String valuez) {
        this.valuez = valuez;
    }

    public Integer getOrderz() {
        return orderz;
    }

    public EnumSsStatus orderz(Integer orderz) {
        this.orderz = orderz;
        return this;
    }

    public void setOrderz(Integer orderz) {
        this.orderz = orderz;
    }

    public String getTenentCode() {
        return tenentCode;
    }

    public EnumSsStatus tenentCode(String tenentCode) {
        this.tenentCode = tenentCode;
        return this;
    }

    public void setTenentCode(String tenentCode) {
        this.tenentCode = tenentCode;
    }

    public EnumSsStatus getParent() {
        return parent;
    }

    public EnumSsStatus parent(EnumSsStatus enumSsStatus) {
        this.parent = enumSsStatus;
        return this;
    }

    public void setParent(EnumSsStatus enumSsStatus) {
        this.parent = enumSsStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EnumSsStatus)) {
            return false;
        }
        return id != null && id.equals(((EnumSsStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EnumSsStatus{" +
            "id=" + getId() +
            ", valuez='" + getValuez() + "'" +
            ", orderz=" + getOrderz() +
            ", tenentCode='" + getTenentCode() + "'" +
            "}";
    }
}
