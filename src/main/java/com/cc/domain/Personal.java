package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

import com.cc.domain.enumeration.BloodType;

/**
 * 私人信息（多对一 员工）
 */
@Entity
@Table(name = "personal")
public class Personal implements Serializable {

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
     * 花名
     */
    @Column(name = "stage_name")
    private String stageName;

    /**
     * 证件姓名
     */
    @Column(name = "id_name")
    private String idName;

    /**
     * 民族
     */
    @Column(name = "nation")
    private String nation;

    /**
     * 户口所在地
     */
    @Column(name = "account_loc")
    private String accountLoc;

    /**
     * 籍贯
     */
    @Column(name = "native_place")
    private String nativePlace;

    /**
     * 居住地址
     */
    @Column(name = "current_addr")
    private String currentAddr;

    /**
     * 配偶姓名
     */
    @Column(name = "spouse_name")
    private String spouseName;

    /**
     * 孩子姓名
     */
    @Column(name = "child_name")
    private String childName;

    /**
     * 血型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "blood_type")
    private BloodType bloodType;

    /**
     * 紧急联系人姓名
     */
    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    /**
     * 紧急联系人电话
     */
    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;

    /**
     * QQ
     */
    @Column(name = "qq")
    private String qq;

    /**
     * 微信
     */
    @Column(name = "wechat")
    private String wechat;

    /**
     * 个人邮箱
     */
    @Column(name = "personal_email")
    private String personalEmail;

    /**
     * 备注信息
     */
    @Column(name = "remark")
    private String remark;

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

    /**
     * 户口类型
     */
    @ManyToOne
    @JsonIgnoreProperties("personals")
    private EnumAccountType accountType;

    /**
     * 最高学历
     */
    @ManyToOne
    @JsonIgnoreProperties("personals")
    private EnumHighestEducation highestEducation;

    /**
     * 政治面貌
     */
    @ManyToOne
    @JsonIgnoreProperties("personals")
    private EnumPoliticsStatus politicsStatus;

    /**
     * 婚姻状况
     */
    @ManyToOne
    @JsonIgnoreProperties("personals")
    private EnumMaritalStatus maritalStatus;

    @ManyToOne
    @JsonIgnoreProperties("personals")
    private Employee emp;

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

    public Personal code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStageName() {
        return stageName;
    }

    public Personal stageName(String stageName) {
        this.stageName = stageName;
        return this;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getIdName() {
        return idName;
    }

    public Personal idName(String idName) {
        this.idName = idName;
        return this;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getNation() {
        return nation;
    }

    public Personal nation(String nation) {
        this.nation = nation;
        return this;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAccountLoc() {
        return accountLoc;
    }

    public Personal accountLoc(String accountLoc) {
        this.accountLoc = accountLoc;
        return this;
    }

    public void setAccountLoc(String accountLoc) {
        this.accountLoc = accountLoc;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public Personal nativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
        return this;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getCurrentAddr() {
        return currentAddr;
    }

    public Personal currentAddr(String currentAddr) {
        this.currentAddr = currentAddr;
        return this;
    }

    public void setCurrentAddr(String currentAddr) {
        this.currentAddr = currentAddr;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public Personal spouseName(String spouseName) {
        this.spouseName = spouseName;
        return this;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getChildName() {
        return childName;
    }

    public Personal childName(String childName) {
        this.childName = childName;
        return this;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public Personal bloodType(BloodType bloodType) {
        this.bloodType = bloodType;
        return this;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public Personal emergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
        return this;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public Personal emergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
        return this;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getQq() {
        return qq;
    }

    public Personal qq(String qq) {
        this.qq = qq;
        return this;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public Personal wechat(String wechat) {
        this.wechat = wechat;
        return this;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public Personal personalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
        return this;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getRemark() {
        return remark;
    }

    public Personal remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOthers() {
        return others;
    }

    public Personal others(String others) {
        this.others = others;
        return this;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public Personal isSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
        return this;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public Personal isHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
        return this;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public EnumAccountType getAccountType() {
        return accountType;
    }

    public Personal accountType(EnumAccountType enumAccountType) {
        this.accountType = enumAccountType;
        return this;
    }

    public void setAccountType(EnumAccountType enumAccountType) {
        this.accountType = enumAccountType;
    }

    public EnumHighestEducation getHighestEducation() {
        return highestEducation;
    }

    public Personal highestEducation(EnumHighestEducation enumHighestEducation) {
        this.highestEducation = enumHighestEducation;
        return this;
    }

    public void setHighestEducation(EnumHighestEducation enumHighestEducation) {
        this.highestEducation = enumHighestEducation;
    }

    public EnumPoliticsStatus getPoliticsStatus() {
        return politicsStatus;
    }

    public Personal politicsStatus(EnumPoliticsStatus enumPoliticsStatus) {
        this.politicsStatus = enumPoliticsStatus;
        return this;
    }

    public void setPoliticsStatus(EnumPoliticsStatus enumPoliticsStatus) {
        this.politicsStatus = enumPoliticsStatus;
    }

    public EnumMaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Personal maritalStatus(EnumMaritalStatus enumMaritalStatus) {
        this.maritalStatus = enumMaritalStatus;
        return this;
    }

    public void setMaritalStatus(EnumMaritalStatus enumMaritalStatus) {
        this.maritalStatus = enumMaritalStatus;
    }

    public Employee getEmp() {
        return emp;
    }

    public Personal emp(Employee employee) {
        this.emp = employee;
        return this;
    }

    public void setEmp(Employee employee) {
        this.emp = employee;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Personal)) {
            return false;
        }
        return id != null && id.equals(((Personal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Personal{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", stageName='" + getStageName() + "'" +
            ", idName='" + getIdName() + "'" +
            ", nation='" + getNation() + "'" +
            ", accountLoc='" + getAccountLoc() + "'" +
            ", nativePlace='" + getNativePlace() + "'" +
            ", currentAddr='" + getCurrentAddr() + "'" +
            ", spouseName='" + getSpouseName() + "'" +
            ", childName='" + getChildName() + "'" +
            ", bloodType='" + getBloodType() + "'" +
            ", emergencyContactName='" + getEmergencyContactName() + "'" +
            ", emergencyContactPhone='" + getEmergencyContactPhone() + "'" +
            ", qq='" + getQq() + "'" +
            ", wechat='" + getWechat() + "'" +
            ", personalEmail='" + getPersonalEmail() + "'" +
            ", remark='" + getRemark() + "'" +
            ", others='" + getOthers() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            "}";
    }
}
