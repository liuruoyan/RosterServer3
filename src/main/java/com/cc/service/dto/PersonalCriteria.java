package com.cc.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.cc.domain.enumeration.BloodType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.cc.domain.Personal} entity. This class is used
 * in {@link com.cc.web.rest.PersonalResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /personals?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PersonalCriteria implements Serializable, Criteria {
    /**
     * Class for filtering BloodType
     */
    public static class BloodTypeFilter extends Filter<BloodType> {

        public BloodTypeFilter() {
        }

        public BloodTypeFilter(BloodTypeFilter filter) {
            super(filter);
        }

        @Override
        public BloodTypeFilter copy() {
            return new BloodTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter stageName;

    private StringFilter idName;

    private StringFilter nation;

    private StringFilter accountLoc;

    private StringFilter nativePlace;

    private StringFilter currentAddr;

    private StringFilter spouseName;

    private StringFilter childName;

    private BloodTypeFilter bloodType;

    private StringFilter emergencyContactName;

    private StringFilter emergencyContactPhone;

    private StringFilter qq;

    private StringFilter wechat;

    private StringFilter personalEmail;

    private StringFilter remark;

    private StringFilter others;

    private BooleanFilter isSelfVerify;

    private BooleanFilter isHrVerify;

    private LongFilter accountTypeId;

    private LongFilter highestEducationId;

    private LongFilter politicsStatusId;

    private LongFilter maritalStatusId;

    private LongFilter empId;

    public PersonalCriteria(){
    }

    public PersonalCriteria(PersonalCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.stageName = other.stageName == null ? null : other.stageName.copy();
        this.idName = other.idName == null ? null : other.idName.copy();
        this.nation = other.nation == null ? null : other.nation.copy();
        this.accountLoc = other.accountLoc == null ? null : other.accountLoc.copy();
        this.nativePlace = other.nativePlace == null ? null : other.nativePlace.copy();
        this.currentAddr = other.currentAddr == null ? null : other.currentAddr.copy();
        this.spouseName = other.spouseName == null ? null : other.spouseName.copy();
        this.childName = other.childName == null ? null : other.childName.copy();
        this.bloodType = other.bloodType == null ? null : other.bloodType.copy();
        this.emergencyContactName = other.emergencyContactName == null ? null : other.emergencyContactName.copy();
        this.emergencyContactPhone = other.emergencyContactPhone == null ? null : other.emergencyContactPhone.copy();
        this.qq = other.qq == null ? null : other.qq.copy();
        this.wechat = other.wechat == null ? null : other.wechat.copy();
        this.personalEmail = other.personalEmail == null ? null : other.personalEmail.copy();
        this.remark = other.remark == null ? null : other.remark.copy();
        this.others = other.others == null ? null : other.others.copy();
        this.isSelfVerify = other.isSelfVerify == null ? null : other.isSelfVerify.copy();
        this.isHrVerify = other.isHrVerify == null ? null : other.isHrVerify.copy();
        this.accountTypeId = other.accountTypeId == null ? null : other.accountTypeId.copy();
        this.highestEducationId = other.highestEducationId == null ? null : other.highestEducationId.copy();
        this.politicsStatusId = other.politicsStatusId == null ? null : other.politicsStatusId.copy();
        this.maritalStatusId = other.maritalStatusId == null ? null : other.maritalStatusId.copy();
        this.empId = other.empId == null ? null : other.empId.copy();
    }

    @Override
    public PersonalCriteria copy() {
        return new PersonalCriteria(this);
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

    public StringFilter getStageName() {
        return stageName;
    }

    public void setStageName(StringFilter stageName) {
        this.stageName = stageName;
    }

    public StringFilter getIdName() {
        return idName;
    }

    public void setIdName(StringFilter idName) {
        this.idName = idName;
    }

    public StringFilter getNation() {
        return nation;
    }

    public void setNation(StringFilter nation) {
        this.nation = nation;
    }

    public StringFilter getAccountLoc() {
        return accountLoc;
    }

    public void setAccountLoc(StringFilter accountLoc) {
        this.accountLoc = accountLoc;
    }

    public StringFilter getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(StringFilter nativePlace) {
        this.nativePlace = nativePlace;
    }

    public StringFilter getCurrentAddr() {
        return currentAddr;
    }

    public void setCurrentAddr(StringFilter currentAddr) {
        this.currentAddr = currentAddr;
    }

    public StringFilter getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(StringFilter spouseName) {
        this.spouseName = spouseName;
    }

    public StringFilter getChildName() {
        return childName;
    }

    public void setChildName(StringFilter childName) {
        this.childName = childName;
    }

    public BloodTypeFilter getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodTypeFilter bloodType) {
        this.bloodType = bloodType;
    }

    public StringFilter getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(StringFilter emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public StringFilter getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(StringFilter emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public StringFilter getQq() {
        return qq;
    }

    public void setQq(StringFilter qq) {
        this.qq = qq;
    }

    public StringFilter getWechat() {
        return wechat;
    }

    public void setWechat(StringFilter wechat) {
        this.wechat = wechat;
    }

    public StringFilter getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(StringFilter personalEmail) {
        this.personalEmail = personalEmail;
    }

    public StringFilter getRemark() {
        return remark;
    }

    public void setRemark(StringFilter remark) {
        this.remark = remark;
    }

    public StringFilter getOthers() {
        return others;
    }

    public void setOthers(StringFilter others) {
        this.others = others;
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

    public LongFilter getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(LongFilter accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public LongFilter getHighestEducationId() {
        return highestEducationId;
    }

    public void setHighestEducationId(LongFilter highestEducationId) {
        this.highestEducationId = highestEducationId;
    }

    public LongFilter getPoliticsStatusId() {
        return politicsStatusId;
    }

    public void setPoliticsStatusId(LongFilter politicsStatusId) {
        this.politicsStatusId = politicsStatusId;
    }

    public LongFilter getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(LongFilter maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
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
        final PersonalCriteria that = (PersonalCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(stageName, that.stageName) &&
            Objects.equals(idName, that.idName) &&
            Objects.equals(nation, that.nation) &&
            Objects.equals(accountLoc, that.accountLoc) &&
            Objects.equals(nativePlace, that.nativePlace) &&
            Objects.equals(currentAddr, that.currentAddr) &&
            Objects.equals(spouseName, that.spouseName) &&
            Objects.equals(childName, that.childName) &&
            Objects.equals(bloodType, that.bloodType) &&
            Objects.equals(emergencyContactName, that.emergencyContactName) &&
            Objects.equals(emergencyContactPhone, that.emergencyContactPhone) &&
            Objects.equals(qq, that.qq) &&
            Objects.equals(wechat, that.wechat) &&
            Objects.equals(personalEmail, that.personalEmail) &&
            Objects.equals(remark, that.remark) &&
            Objects.equals(others, that.others) &&
            Objects.equals(isSelfVerify, that.isSelfVerify) &&
            Objects.equals(isHrVerify, that.isHrVerify) &&
            Objects.equals(accountTypeId, that.accountTypeId) &&
            Objects.equals(highestEducationId, that.highestEducationId) &&
            Objects.equals(politicsStatusId, that.politicsStatusId) &&
            Objects.equals(maritalStatusId, that.maritalStatusId) &&
            Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        stageName,
        idName,
        nation,
        accountLoc,
        nativePlace,
        currentAddr,
        spouseName,
        childName,
        bloodType,
        emergencyContactName,
        emergencyContactPhone,
        qq,
        wechat,
        personalEmail,
        remark,
        others,
        isSelfVerify,
        isHrVerify,
        accountTypeId,
        highestEducationId,
        politicsStatusId,
        maritalStatusId,
        empId
        );
    }

    @Override
    public String toString() {
        return "PersonalCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (stageName != null ? "stageName=" + stageName + ", " : "") +
                (idName != null ? "idName=" + idName + ", " : "") +
                (nation != null ? "nation=" + nation + ", " : "") +
                (accountLoc != null ? "accountLoc=" + accountLoc + ", " : "") +
                (nativePlace != null ? "nativePlace=" + nativePlace + ", " : "") +
                (currentAddr != null ? "currentAddr=" + currentAddr + ", " : "") +
                (spouseName != null ? "spouseName=" + spouseName + ", " : "") +
                (childName != null ? "childName=" + childName + ", " : "") +
                (bloodType != null ? "bloodType=" + bloodType + ", " : "") +
                (emergencyContactName != null ? "emergencyContactName=" + emergencyContactName + ", " : "") +
                (emergencyContactPhone != null ? "emergencyContactPhone=" + emergencyContactPhone + ", " : "") +
                (qq != null ? "qq=" + qq + ", " : "") +
                (wechat != null ? "wechat=" + wechat + ", " : "") +
                (personalEmail != null ? "personalEmail=" + personalEmail + ", " : "") +
                (remark != null ? "remark=" + remark + ", " : "") +
                (others != null ? "others=" + others + ", " : "") +
                (isSelfVerify != null ? "isSelfVerify=" + isSelfVerify + ", " : "") +
                (isHrVerify != null ? "isHrVerify=" + isHrVerify + ", " : "") +
                (accountTypeId != null ? "accountTypeId=" + accountTypeId + ", " : "") +
                (highestEducationId != null ? "highestEducationId=" + highestEducationId + ", " : "") +
                (politicsStatusId != null ? "politicsStatusId=" + politicsStatusId + ", " : "") +
                (maritalStatusId != null ? "maritalStatusId=" + maritalStatusId + ", " : "") +
                (empId != null ? "empId=" + empId + ", " : "") +
            "}";
    }

}
