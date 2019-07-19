package com.cc.domain;

import com.cc.domain.enumeration.BirthType;
import com.cc.service.util.ValidUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.my.tools.core.regex.Regexs;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * 员工
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 编码
     */
    @Column(name = "code")
    private String code;

    /**
     * 员工姓名
     */
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 证件号
     */
    @NotNull
    @Column(name = "id_number", nullable = false)
    private String idNumber;


    /**
     * 手机号
     */
    @NotNull
    @Pattern(regexp = ValidUtil.Regexs.PHONE, message = "手机号码格式不正确")
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * 入职日期
     */
    @NotNull
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    /**
     * 职级
     */
    @Column(name = "job_grade")
    private String jobGrade;

    /**
     * 职务
     */
    @Column(name = "position")
    private String position;

    /**
     * 职位
     */
    @Column(name = "job")
    private String job;

    /**
     * 部门名
     */
    @Column(name = "dept_name")
    private String deptName;

    /**
     * 工号
     */
    @Column(name = "emp_no")
    private String empNo;

    /**
     * 历史工龄
     */
    @Column(name = "seniority")
    private Integer seniority;

    /**
     * 合同公司
     */
    @Column(name = "contractor")
    private String contractor;

    /**
     * 生日类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "birth_type")
    private BirthType birthType;

    /**
     * 出生日期
     */
    @Column(name = "birthday")
    private LocalDate birthday;

    /**
     * 工作地点
     */
    @Column(name = "work_loc")
    private String workLoc;

    /**
     * 联系地址
     */
    @Column(name = "contact_addr")
    private String contactAddr;

    /**
     * 国籍
     */
    @Column(name = "nationality")
    private String nationality;

    /**
     * 名
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * 姓
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * 添加字段
     */
    @Column(name = "others")
    private String others;

    /**
     * 员工是否验证
     */
    @Column(name = "is_self_verify")
    private Boolean isSelfVerify;

    /**
     * 管理（hr）是否验证
     */
    @Column(name = "is_hr_verify")
    private Boolean isHrVerify;

    @OneToMany(mappedBy = "emp")
    private Set<Contract> contracts = new HashSet<>();

    @OneToMany(mappedBy = "emp")
    private Set<Personal> personals = new HashSet<>();

    @OneToMany(mappedBy = "emp")
    private Set<SocialSecurityBenefits> socialSecurityBenefits = new HashSet<>();

    @OneToMany(mappedBy = "emp")
    private Set<PayCard> payCards = new HashSet<>();

    @OneToMany(mappedBy = "emp")
    private Set<Dimission> dimissions = new HashSet<>();

    @OneToMany(mappedBy = "emp")
    private Set<WorkExperience> workExperiences = new HashSet<>();

    @OneToMany(mappedBy = "emp")
    private Set<EducationExperience> educationExperiences = new HashSet<>();

    @OneToMany(mappedBy = "emp")
    private Set<DirectSupervisor> directSupervisors = new HashSet<>();

    @OneToMany(mappedBy = "emp")
    private Set<AdditionalPost> additionalPosts = new HashSet<>();

    /**
     * 员工状态
     */
    @ManyToOne
    @JsonIgnoreProperties("employees")
    private EnumEmpStatus status;

    /**
     * 证件类型
     */
    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("employees")
    private EnumIdType idType;

    /**
     * 合同类型
     */
    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("employees")
    private EnumContractType contractType;

    /**
     * 员工类型
     */
    @ManyToOne
    @JsonIgnoreProperties("employees")
    private EnumEmpType empType;

    /**
     * 性别
     */
    @ManyToOne
    @JsonIgnoreProperties("employees")
    private EnumGender gender;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Employee code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Employee name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public Employee idNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public Employee phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public Employee hireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobGrade() {
        return jobGrade;
    }

    public Employee jobGrade(String jobGrade) {
        this.jobGrade = jobGrade;
        return this;
    }

    public void setJobGrade(String jobGrade) {
        this.jobGrade = jobGrade;
    }

    public String getPosition() {
        return position;
    }

    public Employee position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJob() {
        return job;
    }

    public Employee job(String job) {
        this.job = job;
        return this;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDeptName() {
        return deptName;
    }

    public Employee deptName(String deptName) {
        this.deptName = deptName;
        return this;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmpNo() {
        return empNo;
    }

    public Employee empNo(String empNo) {
        this.empNo = empNo;
        return this;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public Employee seniority(Integer seniority) {
        this.seniority = seniority;
        return this;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public String getContractor() {
        return contractor;
    }

    public Employee contractor(String contractor) {
        this.contractor = contractor;
        return this;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public BirthType getBirthType() {
        return birthType;
    }

    public Employee birthType(BirthType birthType) {
        this.birthType = birthType;
        return this;
    }

    public void setBirthType(BirthType birthType) {
        this.birthType = birthType;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Employee birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getWorkLoc() {
        return workLoc;
    }

    public Employee workLoc(String workLoc) {
        this.workLoc = workLoc;
        return this;
    }

    public void setWorkLoc(String workLoc) {
        this.workLoc = workLoc;
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public Employee contactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
        return this;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    public String getNationality() {
        return nationality;
    }

    public Employee nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOthers() {
        return others;
    }

    public Employee others(String others) {
        this.others = others;
        return this;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public Employee isSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
        return this;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public Employee isHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
        return this;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public Employee contracts(Set<Contract> contracts) {
        this.contracts = contracts;
        return this;
    }

    public Employee addContracts(Contract contract) {
        this.contracts.add(contract);
        contract.setEmp(this);
        return this;
    }

    public Employee removeContracts(Contract contract) {
        this.contracts.remove(contract);
        contract.setEmp(null);
        return this;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public Set<Personal> getPersonals() {
        return personals;
    }

    public Employee personals(Set<Personal> personals) {
        this.personals = personals;
        return this;
    }

    public Employee addPersonals(Personal personal) {
        this.personals.add(personal);
        personal.setEmp(this);
        return this;
    }

    public Employee removePersonals(Personal personal) {
        this.personals.remove(personal);
        personal.setEmp(null);
        return this;
    }

    public void setPersonals(Set<Personal> personals) {
        this.personals = personals;
    }

    public Set<SocialSecurityBenefits> getSocialSecurityBenefits() {
        return socialSecurityBenefits;
    }

    public Employee socialSecurityBenefits(Set<SocialSecurityBenefits> socialSecurityBenefits) {
        this.socialSecurityBenefits = socialSecurityBenefits;
        return this;
    }

    public Employee addSocialSecurityBenefits(SocialSecurityBenefits socialSecurityBenefits) {
        this.socialSecurityBenefits.add(socialSecurityBenefits);
        socialSecurityBenefits.setEmp(this);
        return this;
    }

    public Employee removeSocialSecurityBenefits(SocialSecurityBenefits socialSecurityBenefits) {
        this.socialSecurityBenefits.remove(socialSecurityBenefits);
        socialSecurityBenefits.setEmp(null);
        return this;
    }

    public void setSocialSecurityBenefits(Set<SocialSecurityBenefits> socialSecurityBenefits) {
        this.socialSecurityBenefits = socialSecurityBenefits;
    }

    public Set<PayCard> getPayCards() {
        return payCards;
    }

    public Employee payCards(Set<PayCard> payCards) {
        this.payCards = payCards;
        return this;
    }

    public Employee addPayCards(PayCard payCard) {
        this.payCards.add(payCard);
        payCard.setEmp(this);
        return this;
    }

    public Employee removePayCards(PayCard payCard) {
        this.payCards.remove(payCard);
        payCard.setEmp(null);
        return this;
    }

    public void setPayCards(Set<PayCard> payCards) {
        this.payCards = payCards;
    }

    public Set<Dimission> getDimissions() {
        return dimissions;
    }

    public Employee dimissions(Set<Dimission> dimissions) {
        this.dimissions = dimissions;
        return this;
    }

    public Employee addDimissions(Dimission dimission) {
        this.dimissions.add(dimission);
        dimission.setEmp(this);
        return this;
    }

    public Employee removeDimissions(Dimission dimission) {
        this.dimissions.remove(dimission);
        dimission.setEmp(null);
        return this;
    }

    public void setDimissions(Set<Dimission> dimissions) {
        this.dimissions = dimissions;
    }

    public Set<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public Employee workExperiences(Set<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
        return this;
    }

    public Employee addWorkExperiences(WorkExperience workExperience) {
        this.workExperiences.add(workExperience);
        workExperience.setEmp(this);
        return this;
    }

    public Employee removeWorkExperiences(WorkExperience workExperience) {
        this.workExperiences.remove(workExperience);
        workExperience.setEmp(null);
        return this;
    }

    public void setWorkExperiences(Set<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public Set<EducationExperience> getEducationExperiences() {
        return educationExperiences;
    }

    public Employee educationExperiences(Set<EducationExperience> educationExperiences) {
        this.educationExperiences = educationExperiences;
        return this;
    }

    public Employee addEducationExperiences(EducationExperience educationExperience) {
        this.educationExperiences.add(educationExperience);
        educationExperience.setEmp(this);
        return this;
    }

    public Employee removeEducationExperiences(EducationExperience educationExperience) {
        this.educationExperiences.remove(educationExperience);
        educationExperience.setEmp(null);
        return this;
    }

    public void setEducationExperiences(Set<EducationExperience> educationExperiences) {
        this.educationExperiences = educationExperiences;
    }

    public Set<DirectSupervisor> getDirectSupervisors() {
        return directSupervisors;
    }

    public Employee directSupervisors(Set<DirectSupervisor> directSupervisors) {
        this.directSupervisors = directSupervisors;
        return this;
    }

    public Employee addDirectSupervisors(DirectSupervisor directSupervisor) {
        this.directSupervisors.add(directSupervisor);
        directSupervisor.setEmp(this);
        return this;
    }

    public Employee removeDirectSupervisors(DirectSupervisor directSupervisor) {
        this.directSupervisors.remove(directSupervisor);
        directSupervisor.setEmp(null);
        return this;
    }

    public void setDirectSupervisors(Set<DirectSupervisor> directSupervisors) {
        this.directSupervisors = directSupervisors;
    }

    public Set<AdditionalPost> getAdditionalPosts() {
        return additionalPosts;
    }

    public Employee additionalPosts(Set<AdditionalPost> additionalPosts) {
        this.additionalPosts = additionalPosts;
        return this;
    }

    public Employee addAdditionalPosts(AdditionalPost additionalPost) {
        this.additionalPosts.add(additionalPost);
        additionalPost.setEmp(this);
        return this;
    }

    public Employee removeAdditionalPosts(AdditionalPost additionalPost) {
        this.additionalPosts.remove(additionalPost);
        additionalPost.setEmp(null);
        return this;
    }

    public void setAdditionalPosts(Set<AdditionalPost> additionalPosts) {
        this.additionalPosts = additionalPosts;
    }

    public EnumEmpStatus getStatus() {
        return status;
    }

    public Employee status(EnumEmpStatus enumEmpStatus) {
        this.status = enumEmpStatus;
        return this;
    }

    public void setStatus(EnumEmpStatus enumEmpStatus) {
        this.status = enumEmpStatus;
    }

    public EnumIdType getIdType() {
        return idType;
    }

    public Employee idType(EnumIdType enumIdType) {
        this.idType = enumIdType;
        return this;
    }

    public void setIdType(EnumIdType enumIdType) {
        this.idType = enumIdType;
    }

    public EnumContractType getContractType() {
        return contractType;
    }

    public Employee contractType(EnumContractType enumContractType) {
        this.contractType = enumContractType;
        return this;
    }

    public void setContractType(EnumContractType enumContractType) {
        this.contractType = enumContractType;
    }

    public EnumEmpType getEmpType() {
        return empType;
    }

    public Employee empType(EnumEmpType enumEmpType) {
        this.empType = enumEmpType;
        return this;
    }

    public void setEmpType(EnumEmpType enumEmpType) {
        this.empType = enumEmpType;
    }

    public EnumGender getGender() {
        return gender;
    }

    public Employee gender(EnumGender enumGender) {
        this.gender = enumGender;
        return this;
    }

    public void setGender(EnumGender enumGender) {
        this.gender = enumGender;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", idNumber='" + getIdNumber() + "'" +
            ", phone='" + getPhone() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", jobGrade='" + getJobGrade() + "'" +
            ", position='" + getPosition() + "'" +
            ", job='" + getJob() + "'" +
            ", deptName='" + getDeptName() + "'" +
            ", empNo='" + getEmpNo() + "'" +
            ", seniority=" + getSeniority() +
            ", contractor='" + getContractor() + "'" +
            ", birthType='" + getBirthType() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", workLoc='" + getWorkLoc() + "'" +
            ", contactAddr='" + getContactAddr() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", others='" + getOthers() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            "}";
    }
}
