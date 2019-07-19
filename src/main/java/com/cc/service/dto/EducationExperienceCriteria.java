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
 * Criteria class for the {@link com.cc.domain.EducationExperience} entity. This class is used
 * in {@link com.cc.web.rest.EducationExperienceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /education-experiences?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EducationExperienceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter name;

    private StringFilter phone;

    private StringFilter school;

    private StringFilter major;

    private LocalDateFilter inDate;

    private LocalDateFilter graduationDate;

    private BooleanFilter inception;

    private BooleanFilter isSelfVerify;

    private BooleanFilter isHrVerify;

    private LongFilter educationId;

    private LongFilter empId;

    public EducationExperienceCriteria(){
    }

    public EducationExperienceCriteria(EducationExperienceCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.school = other.school == null ? null : other.school.copy();
        this.major = other.major == null ? null : other.major.copy();
        this.inDate = other.inDate == null ? null : other.inDate.copy();
        this.graduationDate = other.graduationDate == null ? null : other.graduationDate.copy();
        this.inception = other.inception == null ? null : other.inception.copy();
        this.isSelfVerify = other.isSelfVerify == null ? null : other.isSelfVerify.copy();
        this.isHrVerify = other.isHrVerify == null ? null : other.isHrVerify.copy();
        this.educationId = other.educationId == null ? null : other.educationId.copy();
        this.empId = other.empId == null ? null : other.empId.copy();
    }

    @Override
    public EducationExperienceCriteria copy() {
        return new EducationExperienceCriteria(this);
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

    public StringFilter getSchool() {
        return school;
    }

    public void setSchool(StringFilter school) {
        this.school = school;
    }

    public StringFilter getMajor() {
        return major;
    }

    public void setMajor(StringFilter major) {
        this.major = major;
    }

    public LocalDateFilter getInDate() {
        return inDate;
    }

    public void setInDate(LocalDateFilter inDate) {
        this.inDate = inDate;
    }

    public LocalDateFilter getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(LocalDateFilter graduationDate) {
        this.graduationDate = graduationDate;
    }

    public BooleanFilter getInception() {
        return inception;
    }

    public void setInception(BooleanFilter inception) {
        this.inception = inception;
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

    public LongFilter getEducationId() {
        return educationId;
    }

    public void setEducationId(LongFilter educationId) {
        this.educationId = educationId;
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
        final EducationExperienceCriteria that = (EducationExperienceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(name, that.name) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(school, that.school) &&
            Objects.equals(major, that.major) &&
            Objects.equals(inDate, that.inDate) &&
            Objects.equals(graduationDate, that.graduationDate) &&
            Objects.equals(inception, that.inception) &&
            Objects.equals(isSelfVerify, that.isSelfVerify) &&
            Objects.equals(isHrVerify, that.isHrVerify) &&
            Objects.equals(educationId, that.educationId) &&
            Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        name,
        phone,
        school,
        major,
        inDate,
        graduationDate,
        inception,
        isSelfVerify,
        isHrVerify,
        educationId,
        empId
        );
    }

    @Override
    public String toString() {
        return "EducationExperienceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (phone != null ? "phone=" + phone + ", " : "") +
                (school != null ? "school=" + school + ", " : "") +
                (major != null ? "major=" + major + ", " : "") +
                (inDate != null ? "inDate=" + inDate + ", " : "") +
                (graduationDate != null ? "graduationDate=" + graduationDate + ", " : "") +
                (inception != null ? "inception=" + inception + ", " : "") +
                (isSelfVerify != null ? "isSelfVerify=" + isSelfVerify + ", " : "") +
                (isHrVerify != null ? "isHrVerify=" + isHrVerify + ", " : "") +
                (educationId != null ? "educationId=" + educationId + ", " : "") +
                (empId != null ? "empId=" + empId + ", " : "") +
            "}";
    }

}
