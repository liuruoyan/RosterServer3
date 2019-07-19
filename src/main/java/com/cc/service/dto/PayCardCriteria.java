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
 * Criteria class for the {@link com.cc.domain.PayCard} entity. This class is used
 * in {@link com.cc.web.rest.PayCardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pay-cards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PayCardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter branch;

    private StringFilter accountName;

    private StringFilter bankAccount;

    private BooleanFilter isSelfVerify;

    private BooleanFilter isHrVerify;

    private LongFilter depositBankId;

    private LongFilter empId;

    public PayCardCriteria(){
    }

    public PayCardCriteria(PayCardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.branch = other.branch == null ? null : other.branch.copy();
        this.accountName = other.accountName == null ? null : other.accountName.copy();
        this.bankAccount = other.bankAccount == null ? null : other.bankAccount.copy();
        this.isSelfVerify = other.isSelfVerify == null ? null : other.isSelfVerify.copy();
        this.isHrVerify = other.isHrVerify == null ? null : other.isHrVerify.copy();
        this.depositBankId = other.depositBankId == null ? null : other.depositBankId.copy();
        this.empId = other.empId == null ? null : other.empId.copy();
    }

    @Override
    public PayCardCriteria copy() {
        return new PayCardCriteria(this);
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

    public StringFilter getBranch() {
        return branch;
    }

    public void setBranch(StringFilter branch) {
        this.branch = branch;
    }

    public StringFilter getAccountName() {
        return accountName;
    }

    public void setAccountName(StringFilter accountName) {
        this.accountName = accountName;
    }

    public StringFilter getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(StringFilter bankAccount) {
        this.bankAccount = bankAccount;
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

    public LongFilter getDepositBankId() {
        return depositBankId;
    }

    public void setDepositBankId(LongFilter depositBankId) {
        this.depositBankId = depositBankId;
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
        final PayCardCriteria that = (PayCardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(branch, that.branch) &&
            Objects.equals(accountName, that.accountName) &&
            Objects.equals(bankAccount, that.bankAccount) &&
            Objects.equals(isSelfVerify, that.isSelfVerify) &&
            Objects.equals(isHrVerify, that.isHrVerify) &&
            Objects.equals(depositBankId, that.depositBankId) &&
            Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        branch,
        accountName,
        bankAccount,
        isSelfVerify,
        isHrVerify,
        depositBankId,
        empId
        );
    }

    @Override
    public String toString() {
        return "PayCardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (branch != null ? "branch=" + branch + ", " : "") +
                (accountName != null ? "accountName=" + accountName + ", " : "") +
                (bankAccount != null ? "bankAccount=" + bankAccount + ", " : "") +
                (isSelfVerify != null ? "isSelfVerify=" + isSelfVerify + ", " : "") +
                (isHrVerify != null ? "isHrVerify=" + isHrVerify + ", " : "") +
                (depositBankId != null ? "depositBankId=" + depositBankId + ", " : "") +
                (empId != null ? "empId=" + empId + ", " : "") +
            "}";
    }

}
