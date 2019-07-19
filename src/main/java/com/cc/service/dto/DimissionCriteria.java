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
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.cc.domain.Dimission} entity. This class is used
 * in {@link com.cc.web.rest.DimissionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dimissions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DimissionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private LocalDateFilter lastDate;

    private StringFilter reason;

    private BooleanFilter isSelfVerify;

    private BooleanFilter isHrVerify;

    private LongFilter dimissionTypeId;

    private LongFilter empId;

    public DimissionCriteria(){
    }

    public DimissionCriteria(DimissionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.lastDate = other.lastDate == null ? null : other.lastDate.copy();
        this.reason = other.reason == null ? null : other.reason.copy();
        this.isSelfVerify = other.isSelfVerify == null ? null : other.isSelfVerify.copy();
        this.isHrVerify = other.isHrVerify == null ? null : other.isHrVerify.copy();
        this.dimissionTypeId = other.dimissionTypeId == null ? null : other.dimissionTypeId.copy();
        this.empId = other.empId == null ? null : other.empId.copy();
    }

    @Override
    public DimissionCriteria copy() {
        return new DimissionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public LocalDateFilter getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDateFilter lastDate) {
        this.lastDate = lastDate;
    }

    public StringFilter getReason() {
        return reason;
    }

    public void setReason(StringFilter reason) {
        this.reason = reason;
    }

    public BooleanFilter getIsSelfVerify() {
        return isSelfVerify;
    }

    public void setIsSelfVerify(BooleanFilter isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public BooleanFilter getIsHrVerify() {
        return isHrVerify;
    }

    public void setIsHrVerify(BooleanFilter isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public LongFilter getDimissionTypeId() {
        return dimissionTypeId;
    }

    public void setDimissionTypeId(LongFilter dimissionTypeId) {
        this.dimissionTypeId = dimissionTypeId;
    }

    public LongFilter getEmpId() {
        return empId;
    }

    public void setEmpId(LongFilter empId) {
        this.empId = empId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DimissionCriteria that = (DimissionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(lastDate, that.lastDate) &&
            Objects.equals(reason, that.reason) &&
            Objects.equals(isSelfVerify, that.isSelfVerify) &&
            Objects.equals(isHrVerify, that.isHrVerify) &&
            Objects.equals(dimissionTypeId, that.dimissionTypeId) &&
            Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        lastDate,
        reason,
        isSelfVerify,
        isHrVerify,
        dimissionTypeId,
        empId
        );
    }

    @Override
    public String toString() {
        return "DimissionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (lastDate != null ? "lastDate=" + lastDate + ", " : "") +
                (reason != null ? "reason=" + reason + ", " : "") +
                (isSelfVerify != null ? "isSelfVerify=" + isSelfVerify + ", " : "") +
                (isHrVerify != null ? "isHrVerify=" + isHrVerify + ", " : "") +
                (dimissionTypeId != null ? "dimissionTypeId=" + dimissionTypeId + ", " : "") +
                (empId != null ? "empId=" + empId + ", " : "") +
            "}";
    }

}
