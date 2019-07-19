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
 * Criteria class for the {@link com.cc.domain.Contract} entity. This class is used
 * in {@link com.cc.web.rest.ContractResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /contracts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ContractCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private LocalDateFilter startDate;

    private LocalDateFilter endDate;

    private StringFilter email;

    private StringFilter workTel;

    private LocalDateFilter probationEndDay;

    private IntegerFilter probationLength;

    private StringFilter other;

    private BooleanFilter isSelfVerify;

    private BooleanFilter isHrVerify;

    private LongFilter contractTypeId;

    private LongFilter empId;

    public ContractCriteria(){
    }

    public ContractCriteria(ContractCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.startDate = other.startDate == null ? null : other.startDate.copy();
        this.endDate = other.endDate == null ? null : other.endDate.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.workTel = other.workTel == null ? null : other.workTel.copy();
        this.probationEndDay = other.probationEndDay == null ? null : other.probationEndDay.copy();
        this.probationLength = other.probationLength == null ? null : other.probationLength.copy();
        this.other = other.other == null ? null : other.other.copy();
        this.isSelfVerify = other.isSelfVerify == null ? null : other.isSelfVerify.copy();
        this.isHrVerify = other.isHrVerify == null ? null : other.isHrVerify.copy();
        this.contractTypeId = other.contractTypeId == null ? null : other.contractTypeId.copy();
        this.empId = other.empId == null ? null : other.empId.copy();
    }

    @Override
    public ContractCriteria copy() {
        return new ContractCriteria(this);
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

    public LocalDateFilter getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateFilter startDate) {
        this.startDate = startDate;
    }

    public LocalDateFilter getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateFilter endDate) {
        this.endDate = endDate;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getWorkTel() {
        return workTel;
    }

    public void setWorkTel(StringFilter workTel) {
        this.workTel = workTel;
    }

    public LocalDateFilter getProbationEndDay() {
        return probationEndDay;
    }

    public void setProbationEndDay(LocalDateFilter probationEndDay) {
        this.probationEndDay = probationEndDay;
    }

    public IntegerFilter getProbationLength() {
        return probationLength;
    }

    public void setProbationLength(IntegerFilter probationLength) {
        this.probationLength = probationLength;
    }

    public StringFilter getOther() {
        return other;
    }

    public void setOther(StringFilter other) {
        this.other = other;
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

    public LongFilter getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(LongFilter contractTypeId) {
        this.contractTypeId = contractTypeId;
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
        final ContractCriteria that = (ContractCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(endDate, that.endDate) &&
            Objects.equals(email, that.email) &&
            Objects.equals(workTel, that.workTel) &&
            Objects.equals(probationEndDay, that.probationEndDay) &&
            Objects.equals(probationLength, that.probationLength) &&
            Objects.equals(other, that.other) &&
            Objects.equals(isSelfVerify, that.isSelfVerify) &&
            Objects.equals(isHrVerify, that.isHrVerify) &&
            Objects.equals(contractTypeId, that.contractTypeId) &&
            Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        startDate,
        endDate,
        email,
        workTel,
        probationEndDay,
        probationLength,
        other,
        isSelfVerify,
        isHrVerify,
        contractTypeId,
        empId
        );
    }

    @Override
    public String toString() {
        return "ContractCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (startDate != null ? "startDate=" + startDate + ", " : "") +
                (endDate != null ? "endDate=" + endDate + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (workTel != null ? "workTel=" + workTel + ", " : "") +
                (probationEndDay != null ? "probationEndDay=" + probationEndDay + ", " : "") +
                (probationLength != null ? "probationLength=" + probationLength + ", " : "") +
                (other != null ? "other=" + other + ", " : "") +
                (isSelfVerify != null ? "isSelfVerify=" + isSelfVerify + ", " : "") +
                (isHrVerify != null ? "isHrVerify=" + isHrVerify + ", " : "") +
                (contractTypeId != null ? "contractTypeId=" + contractTypeId + ", " : "") +
                (empId != null ? "empId=" + empId + ", " : "") +
            "}";
    }

}
