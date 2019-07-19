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
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.cc.domain.SocialSecurityBenefits} entity. This class is used
 * in {@link com.cc.web.rest.SocialSecurityBenefitsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /social-security-benefits?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SocialSecurityBenefitsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter pfAccount;

    private StringFilter spfAccount;

    private LocalDateFilter pfStartMonth;

    private IntegerFilter pfBase;

    private LocalDateFilter pfStopMonth;

    private StringFilter pfRemark;

    private StringFilter ssAccount;

    private StringFilter ssCity;

    private LocalDateFilter ssStartMonth;

    private IntegerFilter ssBase;

    private LocalDateFilter ssStopMonth;

    private StringFilter ssRemark;

    private BigDecimalFilter allowance;

    private StringFilter taxpayer;

    private BooleanFilter isSelfVerify;

    private BooleanFilter isHrVerify;

    private LongFilter pfTypeId;

    private LongFilter pfStatusId;

    private LongFilter providentPaySchemeId;

    private LongFilter socialSecurityPaySchemeId;

    private LongFilter ssStatusId;

    private LongFilter laborTypeId;

    private LongFilter taxerTypeId;

    private LongFilter taxAreaId;

    private LongFilter empId;

    public SocialSecurityBenefitsCriteria(){
    }

    public SocialSecurityBenefitsCriteria(SocialSecurityBenefitsCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.pfAccount = other.pfAccount == null ? null : other.pfAccount.copy();
        this.spfAccount = other.spfAccount == null ? null : other.spfAccount.copy();
        this.pfStartMonth = other.pfStartMonth == null ? null : other.pfStartMonth.copy();
        this.pfBase = other.pfBase == null ? null : other.pfBase.copy();
        this.pfStopMonth = other.pfStopMonth == null ? null : other.pfStopMonth.copy();
        this.pfRemark = other.pfRemark == null ? null : other.pfRemark.copy();
        this.ssAccount = other.ssAccount == null ? null : other.ssAccount.copy();
        this.ssCity = other.ssCity == null ? null : other.ssCity.copy();
        this.ssStartMonth = other.ssStartMonth == null ? null : other.ssStartMonth.copy();
        this.ssBase = other.ssBase == null ? null : other.ssBase.copy();
        this.ssStopMonth = other.ssStopMonth == null ? null : other.ssStopMonth.copy();
        this.ssRemark = other.ssRemark == null ? null : other.ssRemark.copy();
        this.allowance = other.allowance == null ? null : other.allowance.copy();
        this.taxpayer = other.taxpayer == null ? null : other.taxpayer.copy();
        this.isSelfVerify = other.isSelfVerify == null ? null : other.isSelfVerify.copy();
        this.isHrVerify = other.isHrVerify == null ? null : other.isHrVerify.copy();
        this.pfTypeId = other.pfTypeId == null ? null : other.pfTypeId.copy();
        this.pfStatusId = other.pfStatusId == null ? null : other.pfStatusId.copy();
        this.providentPaySchemeId = other.providentPaySchemeId == null ? null : other.providentPaySchemeId.copy();
        this.socialSecurityPaySchemeId = other.socialSecurityPaySchemeId == null ? null : other.socialSecurityPaySchemeId.copy();
        this.ssStatusId = other.ssStatusId == null ? null : other.ssStatusId.copy();
        this.laborTypeId = other.laborTypeId == null ? null : other.laborTypeId.copy();
        this.taxerTypeId = other.taxerTypeId == null ? null : other.taxerTypeId.copy();
        this.taxAreaId = other.taxAreaId == null ? null : other.taxAreaId.copy();
        this.empId = other.empId == null ? null : other.empId.copy();
    }

    @Override
    public SocialSecurityBenefitsCriteria copy() {
        return new SocialSecurityBenefitsCriteria(this);
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

    public StringFilter getPfAccount() {
        return pfAccount;
    }

    public void setPfAccount(StringFilter pfAccount) {
        this.pfAccount = pfAccount;
    }

    public StringFilter getSpfAccount() {
        return spfAccount;
    }

    public void setSpfAccount(StringFilter spfAccount) {
        this.spfAccount = spfAccount;
    }

    public LocalDateFilter getPfStartMonth() {
        return pfStartMonth;
    }

    public void setPfStartMonth(LocalDateFilter pfStartMonth) {
        this.pfStartMonth = pfStartMonth;
    }

    public IntegerFilter getPfBase() {
        return pfBase;
    }

    public void setPfBase(IntegerFilter pfBase) {
        this.pfBase = pfBase;
    }

    public LocalDateFilter getPfStopMonth() {
        return pfStopMonth;
    }

    public void setPfStopMonth(LocalDateFilter pfStopMonth) {
        this.pfStopMonth = pfStopMonth;
    }

    public StringFilter getPfRemark() {
        return pfRemark;
    }

    public void setPfRemark(StringFilter pfRemark) {
        this.pfRemark = pfRemark;
    }

    public StringFilter getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(StringFilter ssAccount) {
        this.ssAccount = ssAccount;
    }

    public StringFilter getSsCity() {
        return ssCity;
    }

    public void setSsCity(StringFilter ssCity) {
        this.ssCity = ssCity;
    }

    public LocalDateFilter getSsStartMonth() {
        return ssStartMonth;
    }

    public void setSsStartMonth(LocalDateFilter ssStartMonth) {
        this.ssStartMonth = ssStartMonth;
    }

    public IntegerFilter getSsBase() {
        return ssBase;
    }

    public void setSsBase(IntegerFilter ssBase) {
        this.ssBase = ssBase;
    }

    public LocalDateFilter getSsStopMonth() {
        return ssStopMonth;
    }

    public void setSsStopMonth(LocalDateFilter ssStopMonth) {
        this.ssStopMonth = ssStopMonth;
    }

    public StringFilter getSsRemark() {
        return ssRemark;
    }

    public void setSsRemark(StringFilter ssRemark) {
        this.ssRemark = ssRemark;
    }

    public BigDecimalFilter getAllowance() {
        return allowance;
    }

    public void setAllowance(BigDecimalFilter allowance) {
        this.allowance = allowance;
    }

    public StringFilter getTaxpayer() {
        return taxpayer;
    }

    public void setTaxpayer(StringFilter taxpayer) {
        this.taxpayer = taxpayer;
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

    public LongFilter getPfTypeId() {
        return pfTypeId;
    }

    public void setPfTypeId(LongFilter pfTypeId) {
        this.pfTypeId = pfTypeId;
    }

    public LongFilter getPfStatusId() {
        return pfStatusId;
    }

    public void setPfStatusId(LongFilter pfStatusId) {
        this.pfStatusId = pfStatusId;
    }

    public LongFilter getProvidentPaySchemeId() {
        return providentPaySchemeId;
    }

    public void setProvidentPaySchemeId(LongFilter providentPaySchemeId) {
        this.providentPaySchemeId = providentPaySchemeId;
    }

    public LongFilter getSocialSecurityPaySchemeId() {
        return socialSecurityPaySchemeId;
    }

    public void setSocialSecurityPaySchemeId(LongFilter socialSecurityPaySchemeId) {
        this.socialSecurityPaySchemeId = socialSecurityPaySchemeId;
    }

    public LongFilter getSsStatusId() {
        return ssStatusId;
    }

    public void setSsStatusId(LongFilter ssStatusId) {
        this.ssStatusId = ssStatusId;
    }

    public LongFilter getLaborTypeId() {
        return laborTypeId;
    }

    public void setLaborTypeId(LongFilter laborTypeId) {
        this.laborTypeId = laborTypeId;
    }

    public LongFilter getTaxerTypeId() {
        return taxerTypeId;
    }

    public void setTaxerTypeId(LongFilter taxerTypeId) {
        this.taxerTypeId = taxerTypeId;
    }

    public LongFilter getTaxAreaId() {
        return taxAreaId;
    }

    public void setTaxAreaId(LongFilter taxAreaId) {
        this.taxAreaId = taxAreaId;
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
        final SocialSecurityBenefitsCriteria that = (SocialSecurityBenefitsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(pfAccount, that.pfAccount) &&
            Objects.equals(spfAccount, that.spfAccount) &&
            Objects.equals(pfStartMonth, that.pfStartMonth) &&
            Objects.equals(pfBase, that.pfBase) &&
            Objects.equals(pfStopMonth, that.pfStopMonth) &&
            Objects.equals(pfRemark, that.pfRemark) &&
            Objects.equals(ssAccount, that.ssAccount) &&
            Objects.equals(ssCity, that.ssCity) &&
            Objects.equals(ssStartMonth, that.ssStartMonth) &&
            Objects.equals(ssBase, that.ssBase) &&
            Objects.equals(ssStopMonth, that.ssStopMonth) &&
            Objects.equals(ssRemark, that.ssRemark) &&
            Objects.equals(allowance, that.allowance) &&
            Objects.equals(taxpayer, that.taxpayer) &&
            Objects.equals(isSelfVerify, that.isSelfVerify) &&
            Objects.equals(isHrVerify, that.isHrVerify) &&
            Objects.equals(pfTypeId, that.pfTypeId) &&
            Objects.equals(pfStatusId, that.pfStatusId) &&
            Objects.equals(providentPaySchemeId, that.providentPaySchemeId) &&
            Objects.equals(socialSecurityPaySchemeId, that.socialSecurityPaySchemeId) &&
            Objects.equals(ssStatusId, that.ssStatusId) &&
            Objects.equals(laborTypeId, that.laborTypeId) &&
            Objects.equals(taxerTypeId, that.taxerTypeId) &&
            Objects.equals(taxAreaId, that.taxAreaId) &&
            Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        pfAccount,
        spfAccount,
        pfStartMonth,
        pfBase,
        pfStopMonth,
        pfRemark,
        ssAccount,
        ssCity,
        ssStartMonth,
        ssBase,
        ssStopMonth,
        ssRemark,
        allowance,
        taxpayer,
        isSelfVerify,
        isHrVerify,
        pfTypeId,
        pfStatusId,
        providentPaySchemeId,
        socialSecurityPaySchemeId,
        ssStatusId,
        laborTypeId,
        taxerTypeId,
        taxAreaId,
        empId
        );
    }

    @Override
    public String toString() {
        return "SocialSecurityBenefitsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (pfAccount != null ? "pfAccount=" + pfAccount + ", " : "") +
                (spfAccount != null ? "spfAccount=" + spfAccount + ", " : "") +
                (pfStartMonth != null ? "pfStartMonth=" + pfStartMonth + ", " : "") +
                (pfBase != null ? "pfBase=" + pfBase + ", " : "") +
                (pfStopMonth != null ? "pfStopMonth=" + pfStopMonth + ", " : "") +
                (pfRemark != null ? "pfRemark=" + pfRemark + ", " : "") +
                (ssAccount != null ? "ssAccount=" + ssAccount + ", " : "") +
                (ssCity != null ? "ssCity=" + ssCity + ", " : "") +
                (ssStartMonth != null ? "ssStartMonth=" + ssStartMonth + ", " : "") +
                (ssBase != null ? "ssBase=" + ssBase + ", " : "") +
                (ssStopMonth != null ? "ssStopMonth=" + ssStopMonth + ", " : "") +
                (ssRemark != null ? "ssRemark=" + ssRemark + ", " : "") +
                (allowance != null ? "allowance=" + allowance + ", " : "") +
                (taxpayer != null ? "taxpayer=" + taxpayer + ", " : "") +
                (isSelfVerify != null ? "isSelfVerify=" + isSelfVerify + ", " : "") +
                (isHrVerify != null ? "isHrVerify=" + isHrVerify + ", " : "") +
                (pfTypeId != null ? "pfTypeId=" + pfTypeId + ", " : "") +
                (pfStatusId != null ? "pfStatusId=" + pfStatusId + ", " : "") +
                (providentPaySchemeId != null ? "providentPaySchemeId=" + providentPaySchemeId + ", " : "") +
                (socialSecurityPaySchemeId != null ? "socialSecurityPaySchemeId=" + socialSecurityPaySchemeId + ", " : "") +
                (ssStatusId != null ? "ssStatusId=" + ssStatusId + ", " : "") +
                (laborTypeId != null ? "laborTypeId=" + laborTypeId + ", " : "") +
                (taxerTypeId != null ? "taxerTypeId=" + taxerTypeId + ", " : "") +
                (taxAreaId != null ? "taxAreaId=" + taxAreaId + ", " : "") +
                (empId != null ? "empId=" + empId + ", " : "") +
            "}";
    }

}
