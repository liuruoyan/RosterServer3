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
 * Criteria class for the {@link com.cc.domain.WorkExperience} entity. This class is used
 * in {@link com.cc.web.rest.WorkExperienceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /work-experiences?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class WorkExperienceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter eName;

    private StringFilter phoneNum;

    private StringFilter company;

    private StringFilter job;

    private StringFilter jobDesc;

    private LocalDateFilter hireDate;

    private LocalDateFilter leaveDate;

    private BooleanFilter isSelfVerify;

    private BooleanFilter isHrVerify;

    private LongFilter empId;

    public WorkExperienceCriteria(){
    }

    public WorkExperienceCriteria(WorkExperienceCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.eName = other.eName == null ? null : other.eName.copy();
        this.phoneNum = other.phoneNum == null ? null : other.phoneNum.copy();
        this.company = other.company == null ? null : other.company.copy();
        this.job = other.job == null ? null : other.job.copy();
        this.jobDesc = other.jobDesc == null ? null : other.jobDesc.copy();
        this.hireDate = other.hireDate == null ? null : other.hireDate.copy();
        this.leaveDate = other.leaveDate == null ? null : other.leaveDate.copy();
        this.isSelfVerify = other.isSelfVerify == null ? null : other.isSelfVerify.copy();
        this.isHrVerify = other.isHrVerify == null ? null : other.isHrVerify.copy();
        this.empId = other.empId == null ? null : other.empId.copy();
    }

    @Override
    public WorkExperienceCriteria copy() {
        return new WorkExperienceCriteria(this);
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

    public StringFilter geteName() {
        return eName;
    }

    public void seteName(StringFilter eName) {
        this.eName = eName;
    }

    public StringFilter getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(StringFilter phoneNum) {
        this.phoneNum = phoneNum;
    }

    public StringFilter getCompany() {
        return company;
    }

    public void setCompany(StringFilter company) {
        this.company = company;
    }

    public StringFilter getJob() {
        return job;
    }

    public void setJob(StringFilter job) {
        this.job = job;
    }

    public StringFilter getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(StringFilter jobDesc) {
        this.jobDesc = jobDesc;
    }

    public LocalDateFilter getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateFilter hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDateFilter getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDateFilter leaveDate) {
        this.leaveDate = leaveDate;
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
        final WorkExperienceCriteria that = (WorkExperienceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(eName, that.eName) &&
            Objects.equals(phoneNum, that.phoneNum) &&
            Objects.equals(company, that.company) &&
            Objects.equals(job, that.job) &&
            Objects.equals(jobDesc, that.jobDesc) &&
            Objects.equals(hireDate, that.hireDate) &&
            Objects.equals(leaveDate, that.leaveDate) &&
            Objects.equals(isSelfVerify, that.isSelfVerify) &&
            Objects.equals(isHrVerify, that.isHrVerify) &&
            Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        eName,
        phoneNum,
        company,
        job,
        jobDesc,
        hireDate,
        leaveDate,
        isSelfVerify,
        isHrVerify,
        empId
        );
    }

    @Override
    public String toString() {
        return "WorkExperienceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (eName != null ? "eName=" + eName + ", " : "") +
                (phoneNum != null ? "phoneNum=" + phoneNum + ", " : "") +
                (company != null ? "company=" + company + ", " : "") +
                (job != null ? "job=" + job + ", " : "") +
                (jobDesc != null ? "jobDesc=" + jobDesc + ", " : "") +
                (hireDate != null ? "hireDate=" + hireDate + ", " : "") +
                (leaveDate != null ? "leaveDate=" + leaveDate + ", " : "") +
                (isSelfVerify != null ? "isSelfVerify=" + isSelfVerify + ", " : "") +
                (isHrVerify != null ? "isHrVerify=" + isHrVerify + ", " : "") +
                (empId != null ? "empId=" + empId + ", " : "") +
            "}";
    }

}
