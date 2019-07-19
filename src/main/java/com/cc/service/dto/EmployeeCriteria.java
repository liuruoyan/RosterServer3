package com.cc.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.cc.domain.enumeration.BirthType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.cc.domain.Employee} entity. This class is used
 * in {@link com.cc.web.rest.EmployeeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /employees?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmployeeCriteria implements Serializable, Criteria {
    /**
     * Class for filtering BirthType
     */
    public static class BirthTypeFilter extends Filter<BirthType> {

        public BirthTypeFilter() {
        }

        public BirthTypeFilter(BirthTypeFilter filter) {
            super(filter);
        }

        @Override
        public BirthTypeFilter copy() {
            return new BirthTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter name;

    private StringFilter idNumber;

    private StringFilter phone;

    private LocalDateFilter hireDate;

    private StringFilter jobGrade;

    private StringFilter position;

    private StringFilter job;

    private StringFilter deptName;

    private StringFilter empNo;

    private IntegerFilter seniority;

    private StringFilter contractor;

    private BirthTypeFilter birthType;

    private LocalDateFilter birthday;

    private StringFilter workLoc;

    private StringFilter contactAddr;

    private StringFilter nationality;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter others;

    private BooleanFilter isSelfVerify;

    private BooleanFilter isHrVerify;

    private LongFilter contractsId;

    private LongFilter personalsId;

    private LongFilter socialSecurityBenefitsId;

    private LongFilter payCardsId;

    private LongFilter dimissionsId;

    private LongFilter workExperiencesId;

    private LongFilter educationExperiencesId;

    private LongFilter directSupervisorsId;

    private LongFilter additionalPostsId;

    private LongFilter statusId;

    private LongFilter idTypeId;

    private LongFilter contractTypeId;

    private LongFilter empTypeId;

    private LongFilter genderId;

    public EmployeeCriteria(){
    }

    public EmployeeCriteria(EmployeeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.idNumber = other.idNumber == null ? null : other.idNumber.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.hireDate = other.hireDate == null ? null : other.hireDate.copy();
        this.jobGrade = other.jobGrade == null ? null : other.jobGrade.copy();
        this.position = other.position == null ? null : other.position.copy();
        this.job = other.job == null ? null : other.job.copy();
        this.deptName = other.deptName == null ? null : other.deptName.copy();
        this.empNo = other.empNo == null ? null : other.empNo.copy();
        this.seniority = other.seniority == null ? null : other.seniority.copy();
        this.contractor = other.contractor == null ? null : other.contractor.copy();
        this.birthType = other.birthType == null ? null : other.birthType.copy();
        this.birthday = other.birthday == null ? null : other.birthday.copy();
        this.workLoc = other.workLoc == null ? null : other.workLoc.copy();
        this.contactAddr = other.contactAddr == null ? null : other.contactAddr.copy();
        this.nationality = other.nationality == null ? null : other.nationality.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.others = other.others == null ? null : other.others.copy();
        this.isSelfVerify = other.isSelfVerify == null ? null : other.isSelfVerify.copy();
        this.isHrVerify = other.isHrVerify == null ? null : other.isHrVerify.copy();
        this.contractsId = other.contractsId == null ? null : other.contractsId.copy();
        this.personalsId = other.personalsId == null ? null : other.personalsId.copy();
        this.socialSecurityBenefitsId = other.socialSecurityBenefitsId == null ? null : other.socialSecurityBenefitsId.copy();
        this.payCardsId = other.payCardsId == null ? null : other.payCardsId.copy();
        this.dimissionsId = other.dimissionsId == null ? null : other.dimissionsId.copy();
        this.workExperiencesId = other.workExperiencesId == null ? null : other.workExperiencesId.copy();
        this.educationExperiencesId = other.educationExperiencesId == null ? null : other.educationExperiencesId.copy();
        this.directSupervisorsId = other.directSupervisorsId == null ? null : other.directSupervisorsId.copy();
        this.additionalPostsId = other.additionalPostsId == null ? null : other.additionalPostsId.copy();
        this.statusId = other.statusId == null ? null : other.statusId.copy();
        this.idTypeId = other.idTypeId == null ? null : other.idTypeId.copy();
        this.contractTypeId = other.contractTypeId == null ? null : other.contractTypeId.copy();
        this.empTypeId = other.empTypeId == null ? null : other.empTypeId.copy();
        this.genderId = other.genderId == null ? null : other.genderId.copy();
    }

    @Override
    public EmployeeCriteria copy() {
        return new EmployeeCriteria(this);
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

    public StringFilter getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(StringFilter idNumber) {
        this.idNumber = idNumber;
    }

    public StringFilter getPhone() {
        return phone;
    }

    public void setPhone(StringFilter phone) {
        this.phone = phone;
    }

    public LocalDateFilter getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateFilter hireDate) {
        this.hireDate = hireDate;
    }

    public StringFilter getJobGrade() {
        return jobGrade;
    }

    public void setJobGrade(StringFilter jobGrade) {
        this.jobGrade = jobGrade;
    }

    public StringFilter getPosition() {
        return position;
    }

    public void setPosition(StringFilter position) {
        this.position = position;
    }

    public StringFilter getJob() {
        return job;
    }

    public void setJob(StringFilter job) {
        this.job = job;
    }

    public StringFilter getDeptName() {
        return deptName;
    }

    public void setDeptName(StringFilter deptName) {
        this.deptName = deptName;
    }

    public StringFilter getEmpNo() {
        return empNo;
    }

    public void setEmpNo(StringFilter empNo) {
        this.empNo = empNo;
    }

    public IntegerFilter getSeniority() {
        return seniority;
    }

    public void setSeniority(IntegerFilter seniority) {
        this.seniority = seniority;
    }

    public StringFilter getContractor() {
        return contractor;
    }

    public void setContractor(StringFilter contractor) {
        this.contractor = contractor;
    }

    public BirthTypeFilter getBirthType() {
        return birthType;
    }

    public void setBirthType(BirthTypeFilter birthType) {
        this.birthType = birthType;
    }

    public LocalDateFilter getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateFilter birthday) {
        this.birthday = birthday;
    }

    public StringFilter getWorkLoc() {
        return workLoc;
    }

    public void setWorkLoc(StringFilter workLoc) {
        this.workLoc = workLoc;
    }

    public StringFilter getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(StringFilter contactAddr) {
        this.contactAddr = contactAddr;
    }

    public StringFilter getNationality() {
        return nationality;
    }

    public void setNationality(StringFilter nationality) {
        this.nationality = nationality;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
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

    public LongFilter getContractsId() {
        return contractsId;
    }

    public void setContractsId(LongFilter contractsId) {
        this.contractsId = contractsId;
    }

    public LongFilter getPersonalsId() {
        return personalsId;
    }

    public void setPersonalsId(LongFilter personalsId) {
        this.personalsId = personalsId;
    }

    public LongFilter getSocialSecurityBenefitsId() {
        return socialSecurityBenefitsId;
    }

    public void setSocialSecurityBenefitsId(LongFilter socialSecurityBenefitsId) {
        this.socialSecurityBenefitsId = socialSecurityBenefitsId;
    }

    public LongFilter getPayCardsId() {
        return payCardsId;
    }

    public void setPayCardsId(LongFilter payCardsId) {
        this.payCardsId = payCardsId;
    }

    public LongFilter getDimissionsId() {
        return dimissionsId;
    }

    public void setDimissionsId(LongFilter dimissionsId) {
        this.dimissionsId = dimissionsId;
    }

    public LongFilter getWorkExperiencesId() {
        return workExperiencesId;
    }

    public void setWorkExperiencesId(LongFilter workExperiencesId) {
        this.workExperiencesId = workExperiencesId;
    }

    public LongFilter getEducationExperiencesId() {
        return educationExperiencesId;
    }

    public void setEducationExperiencesId(LongFilter educationExperiencesId) {
        this.educationExperiencesId = educationExperiencesId;
    }

    public LongFilter getDirectSupervisorsId() {
        return directSupervisorsId;
    }

    public void setDirectSupervisorsId(LongFilter directSupervisorsId) {
        this.directSupervisorsId = directSupervisorsId;
    }

    public LongFilter getAdditionalPostsId() {
        return additionalPostsId;
    }

    public void setAdditionalPostsId(LongFilter additionalPostsId) {
        this.additionalPostsId = additionalPostsId;
    }

    public LongFilter getStatusId() {
        return statusId;
    }

    public void setStatusId(LongFilter statusId) {
        this.statusId = statusId;
    }

    public LongFilter getIdTypeId() {
        return idTypeId;
    }

    public void setIdTypeId(LongFilter idTypeId) {
        this.idTypeId = idTypeId;
    }

    public LongFilter getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(LongFilter contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public LongFilter getEmpTypeId() {
        return empTypeId;
    }

    public void setEmpTypeId(LongFilter empTypeId) {
        this.empTypeId = empTypeId;
    }

    public LongFilter getGenderId() {
        return genderId;
    }

    public void setGenderId(LongFilter genderId) {
        this.genderId = genderId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmployeeCriteria that = (EmployeeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(name, that.name) &&
            Objects.equals(idNumber, that.idNumber) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(hireDate, that.hireDate) &&
            Objects.equals(jobGrade, that.jobGrade) &&
            Objects.equals(position, that.position) &&
            Objects.equals(job, that.job) &&
            Objects.equals(deptName, that.deptName) &&
            Objects.equals(empNo, that.empNo) &&
            Objects.equals(seniority, that.seniority) &&
            Objects.equals(contractor, that.contractor) &&
            Objects.equals(birthType, that.birthType) &&
            Objects.equals(birthday, that.birthday) &&
            Objects.equals(workLoc, that.workLoc) &&
            Objects.equals(contactAddr, that.contactAddr) &&
            Objects.equals(nationality, that.nationality) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(others, that.others) &&
            Objects.equals(isSelfVerify, that.isSelfVerify) &&
            Objects.equals(isHrVerify, that.isHrVerify) &&
            Objects.equals(contractsId, that.contractsId) &&
            Objects.equals(personalsId, that.personalsId) &&
            Objects.equals(socialSecurityBenefitsId, that.socialSecurityBenefitsId) &&
            Objects.equals(payCardsId, that.payCardsId) &&
            Objects.equals(dimissionsId, that.dimissionsId) &&
            Objects.equals(workExperiencesId, that.workExperiencesId) &&
            Objects.equals(educationExperiencesId, that.educationExperiencesId) &&
            Objects.equals(directSupervisorsId, that.directSupervisorsId) &&
            Objects.equals(additionalPostsId, that.additionalPostsId) &&
            Objects.equals(statusId, that.statusId) &&
            Objects.equals(idTypeId, that.idTypeId) &&
            Objects.equals(contractTypeId, that.contractTypeId) &&
            Objects.equals(empTypeId, that.empTypeId) &&
            Objects.equals(genderId, that.genderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        name,
        idNumber,
        phone,
        hireDate,
        jobGrade,
        position,
        job,
        deptName,
        empNo,
        seniority,
        contractor,
        birthType,
        birthday,
        workLoc,
        contactAddr,
        nationality,
        firstName,
        lastName,
        others,
        isSelfVerify,
        isHrVerify,
        contractsId,
        personalsId,
        socialSecurityBenefitsId,
        payCardsId,
        dimissionsId,
        workExperiencesId,
        educationExperiencesId,
        directSupervisorsId,
        additionalPostsId,
        statusId,
        idTypeId,
        contractTypeId,
        empTypeId,
        genderId
        );
    }

    @Override
    public String toString() {
        return "EmployeeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (idNumber != null ? "idNumber=" + idNumber + ", " : "") +
                (phone != null ? "phone=" + phone + ", " : "") +
                (hireDate != null ? "hireDate=" + hireDate + ", " : "") +
                (jobGrade != null ? "jobGrade=" + jobGrade + ", " : "") +
                (position != null ? "position=" + position + ", " : "") +
                (job != null ? "job=" + job + ", " : "") +
                (deptName != null ? "deptName=" + deptName + ", " : "") +
                (empNo != null ? "empNo=" + empNo + ", " : "") +
                (seniority != null ? "seniority=" + seniority + ", " : "") +
                (contractor != null ? "contractor=" + contractor + ", " : "") +
                (birthType != null ? "birthType=" + birthType + ", " : "") +
                (birthday != null ? "birthday=" + birthday + ", " : "") +
                (workLoc != null ? "workLoc=" + workLoc + ", " : "") +
                (contactAddr != null ? "contactAddr=" + contactAddr + ", " : "") +
                (nationality != null ? "nationality=" + nationality + ", " : "") +
                (firstName != null ? "firstName=" + firstName + ", " : "") +
                (lastName != null ? "lastName=" + lastName + ", " : "") +
                (others != null ? "others=" + others + ", " : "") +
                (isSelfVerify != null ? "isSelfVerify=" + isSelfVerify + ", " : "") +
                (isHrVerify != null ? "isHrVerify=" + isHrVerify + ", " : "") +
                (contractsId != null ? "contractsId=" + contractsId + ", " : "") +
                (personalsId != null ? "personalsId=" + personalsId + ", " : "") +
                (socialSecurityBenefitsId != null ? "socialSecurityBenefitsId=" + socialSecurityBenefitsId + ", " : "") +
                (payCardsId != null ? "payCardsId=" + payCardsId + ", " : "") +
                (dimissionsId != null ? "dimissionsId=" + dimissionsId + ", " : "") +
                (workExperiencesId != null ? "workExperiencesId=" + workExperiencesId + ", " : "") +
                (educationExperiencesId != null ? "educationExperiencesId=" + educationExperiencesId + ", " : "") +
                (directSupervisorsId != null ? "directSupervisorsId=" + directSupervisorsId + ", " : "") +
                (additionalPostsId != null ? "additionalPostsId=" + additionalPostsId + ", " : "") +
                (statusId != null ? "statusId=" + statusId + ", " : "") +
                (idTypeId != null ? "idTypeId=" + idTypeId + ", " : "") +
                (contractTypeId != null ? "contractTypeId=" + contractTypeId + ", " : "") +
                (empTypeId != null ? "empTypeId=" + empTypeId + ", " : "") +
                (genderId != null ? "genderId=" + genderId + ", " : "") +
            "}";
    }

}
