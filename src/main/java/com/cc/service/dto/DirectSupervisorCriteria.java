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
 * Criteria class for the {@link com.cc.domain.DirectSupervisor} entity. This class is used
 * in {@link com.cc.web.rest.DirectSupervisorResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /direct-supervisors?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DirectSupervisorCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter name;

    private StringFilter phone;

    private StringFilter aSupName;

    private StringFilter aSupPhone;

    private StringFilter bSupName;

    private StringFilter bSupPhone;

    private StringFilter fSubName;

    private StringFilter fSubPhone;

    private BooleanFilter isSelfVerify;

    private BooleanFilter isHrVerify;

    private LongFilter empId;

    public DirectSupervisorCriteria(){
    }

    public DirectSupervisorCriteria(DirectSupervisorCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.aSupName = other.aSupName == null ? null : other.aSupName.copy();
        this.aSupPhone = other.aSupPhone == null ? null : other.aSupPhone.copy();
        this.bSupName = other.bSupName == null ? null : other.bSupName.copy();
        this.bSupPhone = other.bSupPhone == null ? null : other.bSupPhone.copy();
        this.fSubName = other.fSubName == null ? null : other.fSubName.copy();
        this.fSubPhone = other.fSubPhone == null ? null : other.fSubPhone.copy();
        this.isSelfVerify = other.isSelfVerify == null ? null : other.isSelfVerify.copy();
        this.isHrVerify = other.isHrVerify == null ? null : other.isHrVerify.copy();
        this.empId = other.empId == null ? null : other.empId.copy();
    }

    @Override
    public DirectSupervisorCriteria copy() {
        return new DirectSupervisorCriteria(this);
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

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getPhone() {
        return phone;
    }

    public void setPhone(StringFilter phone) {
        this.phone = phone;
    }

    public StringFilter getaSupName() {
        return aSupName;
    }

    public void setaSupName(StringFilter aSupName) {
        this.aSupName = aSupName;
    }

    public StringFilter getaSupPhone() {
        return aSupPhone;
    }

    public void setaSupPhone(StringFilter aSupPhone) {
        this.aSupPhone = aSupPhone;
    }

    public StringFilter getbSupName() {
        return bSupName;
    }

    public void setbSupName(StringFilter bSupName) {
        this.bSupName = bSupName;
    }

    public StringFilter getbSupPhone() {
        return bSupPhone;
    }

    public void setbSupPhone(StringFilter bSupPhone) {
        this.bSupPhone = bSupPhone;
    }

    public StringFilter getfSubName() {
        return fSubName;
    }

    public void setfSubName(StringFilter fSubName) {
        this.fSubName = fSubName;
    }

    public StringFilter getfSubPhone() {
        return fSubPhone;
    }

    public void setfSubPhone(StringFilter fSubPhone) {
        this.fSubPhone = fSubPhone;
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
        final DirectSupervisorCriteria that = (DirectSupervisorCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(name, that.name) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(aSupName, that.aSupName) &&
            Objects.equals(aSupPhone, that.aSupPhone) &&
            Objects.equals(bSupName, that.bSupName) &&
            Objects.equals(bSupPhone, that.bSupPhone) &&
            Objects.equals(fSubName, that.fSubName) &&
            Objects.equals(fSubPhone, that.fSubPhone) &&
            Objects.equals(isSelfVerify, that.isSelfVerify) &&
            Objects.equals(isHrVerify, that.isHrVerify) &&
            Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        name,
        phone,
        aSupName,
        aSupPhone,
        bSupName,
        bSupPhone,
        fSubName,
        fSubPhone,
        isSelfVerify,
        isHrVerify,
        empId
        );
    }

    @Override
    public String toString() {
        return "DirectSupervisorCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (phone != null ? "phone=" + phone + ", " : "") +
                (aSupName != null ? "aSupName=" + aSupName + ", " : "") +
                (aSupPhone != null ? "aSupPhone=" + aSupPhone + ", " : "") +
                (bSupName != null ? "bSupName=" + bSupName + ", " : "") +
                (bSupPhone != null ? "bSupPhone=" + bSupPhone + ", " : "") +
                (fSubName != null ? "fSubName=" + fSubName + ", " : "") +
                (fSubPhone != null ? "fSubPhone=" + fSubPhone + ", " : "") +
                (isSelfVerify != null ? "isSelfVerify=" + isSelfVerify + ", " : "") +
                (isHrVerify != null ? "isHrVerify=" + isHrVerify + ", " : "") +
                (empId != null ? "empId=" + empId + ", " : "") +
            "}";
    }

}
