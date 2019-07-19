package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 公积金缴纳方案
 */
@Entity
@Table(name = "enum_pf_pay_scheme")
public class EnumPfPayScheme implements Serializable {

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
    @JsonIgnoreProperties("enumPfPaySchemes")
    private EnumPfPayScheme parent;

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

    public EnumPfPayScheme valuez(String valuez) {
        this.valuez = valuez;
        return this;
    }

    public void setValuez(String valuez) {
        this.valuez = valuez;
    }

    public Integer getOrderz() {
        return orderz;
    }

    public EnumPfPayScheme orderz(Integer orderz) {
        this.orderz = orderz;
        return this;
    }

    public void setOrderz(Integer orderz) {
        this.orderz = orderz;
    }

    public String getTenentCode() {
        return tenentCode;
    }

    public EnumPfPayScheme tenentCode(String tenentCode) {
        this.tenentCode = tenentCode;
        return this;
    }

    public void setTenentCode(String tenentCode) {
        this.tenentCode = tenentCode;
    }

    public EnumPfPayScheme getParent() {
        return parent;
    }

    public EnumPfPayScheme parent(EnumPfPayScheme enumPfPayScheme) {
        this.parent = enumPfPayScheme;
        return this;
    }

    public void setParent(EnumPfPayScheme enumPfPayScheme) {
        this.parent = enumPfPayScheme;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EnumPfPayScheme)) {
            return false;
        }
        return id != null && id.equals(((EnumPfPayScheme) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EnumPfPayScheme{" +
            "id=" + getId() +
            ", valuez='" + getValuez() + "'" +
            ", orderz=" + getOrderz() +
            ", tenentCode='" + getTenentCode() + "'" +
            "}";
    }
}
