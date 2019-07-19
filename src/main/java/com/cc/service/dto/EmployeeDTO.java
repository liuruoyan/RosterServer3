package com.cc.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.cc.domain.enumeration.BirthType;

/**
 * A DTO for the {@link com.cc.domain.Employee} entity.
 */
@ApiModel(description = "员工")
public class EmployeeDTO implements Serializable {

    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 员工姓名
     */
    @NotNull
    @ApiModelProperty(value = "员工姓名", required = true)
    private String name;

    /**
     * 证件号
     */
    @NotNull
    @ApiModelProperty(value = "证件号", required = true)
    private String idNumber;

    /**
     * 手机号
     */
    @NotNull
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    /**
     * 入职日期
     */
    @NotNull
    @ApiModelProperty(value = "入职日期", required = true)
    private LocalDate hireDate;

    /**
     * 职级
     */
    @ApiModelProperty(value = "职级")
    private String jobGrade;

    /**
     * 职务
     */
    @ApiModelProperty(value = "职务")
    private String position;

    /**
     * 职位
     */
    @ApiModelProperty(value = "职位")
    private String job;

    /**
     * 部门名
     */
    @ApiModelProperty(value = "部门名")
    private String deptName;

    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    private String empNo;

    /**
     * 历史工龄
     */
    @ApiModelProperty(value = "历史工龄")
    private Integer seniority;

    /**
     * 合同公司
     */
    @ApiModelProperty(value = "合同公司")
    private String contractor;

    /**
     * 生日类型
     */
    @ApiModelProperty(value = "生日类型")
    private BirthType birthType;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    /**
     * 工作地点
     */
    @ApiModelProperty(value = "工作地点")
    private String workLoc;

    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址")
    private String contactAddr;

    /**
     * 国籍
     */
    @ApiModelProperty(value = "国籍")
    private String nationality;

    /**
     * 名
     */
    @ApiModelProperty(value = "名")
    private String firstName;

    /**
     * 姓
     */
    @ApiModelProperty(value = "姓")
    private String lastName;

    /**
     * 添加字段
     */
    @ApiModelProperty(value = "添加字段")
    private String others;

    /**
     * 员工是否验证
     */
    @ApiModelProperty(value = "员工是否验证")
    private Boolean isSelfVerify;

    /**
     * 管理（hr）是否验证
     */
    @ApiModelProperty(value = "管理（hr）是否验证")
    private Boolean isHrVerify;

    /**
     * 员工状态
     */
    @ApiModelProperty(value = "员工状态")

    private Long statusId;
    /**
     * 证件类型
     */
    @ApiModelProperty(value = "证件类型")

    private Long idTypeId;
    /**
     * 合同类型
     */
    @ApiModelProperty(value = "合同类型")

    private Long contractTypeId;
    /**
     * 员工类型
     */
    @ApiModelProperty(value = "员工类型")

    private Long empTypeId;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")

    private Long genderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobGrade() {
        return jobGrade;
    }

    public void setJobGrade(String jobGrade) {
        this.jobGrade = jobGrade;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public BirthType getBirthType() {
        return birthType;
    }

    public void setBirthType(BirthType birthType) {
        this.birthType = birthType;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getWorkLoc() {
        return workLoc;
    }

    public void setWorkLoc(String workLoc) {
        this.workLoc = workLoc;
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long enumEmpStatusId) {
        this.statusId = enumEmpStatusId;
    }

    public Long getIdTypeId() {
        return idTypeId;
    }

    public void setIdTypeId(Long enumIdTypeId) {
        this.idTypeId = enumIdTypeId;
    }

    public Long getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(Long enumContractTypeId) {
        this.contractTypeId = enumContractTypeId;
    }

    public Long getEmpTypeId() {
        return empTypeId;
    }

    public void setEmpTypeId(Long enumEmpTypeId) {
        this.empTypeId = enumEmpTypeId;
    }

    public Long getGenderId() {
        return genderId;
    }

    public void setGenderId(Long enumGenderId) {
        this.genderId = enumGenderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (employeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
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
            ", status=" + getStatusId() +
            ", idType=" + getIdTypeId() +
            ", contractType=" + getContractTypeId() +
            ", empType=" + getEmpTypeId() +
            ", gender=" + getGenderId() +
            "}";
    }
}
