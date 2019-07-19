package com.cc.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.cc.domain.EnumEmpStatus} entity. This class is used
 * in {@link com.cc.web.rest.EnumEmpStatusResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /enum-emp-statuses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EnumEmpStatusCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter valuez;

    private IntegerFilter orderz;

    private StringFilter tenentCode;

    private LongFilter parentId;

    public EnumEmpStatusCriteria(){
    }

    public EnumEmpStatusCriteria(EnumEmpStatusCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.valuez = other.valuez == null ? null : other.valuez.copy();
        this.orderz = other.orderz == null ? null : other.orderz.copy();
        this.tenentCode = other.tenentCode == null ? null : other.tenentCode.copy();
        this.parentId = other.parentId == null ? null : other.parentId.copy();
    }

    @Override
    public EnumEmpStatusCriteria copy() {
        return new EnumEmpStatusCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getValuez() {
        return valuez;
    }

    public void setValuez(StringFilter valuez) {
        this.valuez = valuez;
    }

    public IntegerFilter getOrderz() {
        return orderz;
    }

    public void setOrderz(IntegerFilter orderz) {
        this.orderz = orderz;
    }

    public StringFilter getTenentCode() {
        return tenentCode;
    }

    public void setTenentCode(StringFilter tenentCode) {
        this.tenentCode = tenentCode;
    }

    public LongFilter getParentId() {
        return parentId;
    }

    public void setParentId(LongFilter parentId) {
        this.parentId = parentId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EnumEmpStatusCriteria that = (EnumEmpStatusCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(valuez, that.valuez) &&
            Objects.equals(orderz, that.orderz) &&
            Objects.equals(tenentCode, that.tenentCode) &&
            Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        valuez,
        orderz,
        tenentCode,
        parentId
        );
    }

    @Override
    public String toString() {
        return "EnumEmpStatusCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (valuez != null ? "valuez=" + valuez + ", " : "") +
                (orderz != null ? "orderz=" + orderz + ", " : "") +
                (tenentCode != null ? "tenentCode=" + tenentCode + ", " : "") +
                (parentId != null ? "parentId=" + parentId + ", " : "") +
            "}";
    }

}
