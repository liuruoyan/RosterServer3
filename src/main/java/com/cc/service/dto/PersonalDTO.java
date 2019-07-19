package com.cc.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import com.cc.domain.enumeration.BloodType;

/**
 * A DTO for the {@link com.cc.domain.Personal} entity.
 */
@ApiModel(description = "私人信息（多对一 员工）")
public class PersonalDTO implements Serializable {

    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 花名
     */
    @ApiModelProperty(value = "花名")
    private String stageName;

    /**
     * 证件姓名
     */
    @ApiModelProperty(value = "证件姓名")
    private String idName;

    /**
     * 民族
     */
    @ApiModelProperty(value = "民族")
    private String nation;

    /**
     * 户口所在地
     */
    @ApiModelProperty(value = "户口所在地")
    private String accountLoc;

    /**
     * 籍贯
     */
    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    /**
     * 居住地址
     */
    @ApiModelProperty(value = "居住地址")
    private String currentAddr;

    /**
     * 配偶姓名
     */
    @ApiModelProperty(value = "配偶姓名")
    private String spouseName;

    /**
     * 孩子姓名
     */
    @ApiModelProperty(value = "孩子姓名")
    private String childName;

    /**
     * 血型
     */
    @ApiModelProperty(value = "血型")
    private BloodType bloodType;

    /**
     * 紧急联系人姓名
     */
    @ApiModelProperty(value = "紧急联系人姓名")
    private String emergencyContactName;

    /**
     * 紧急联系人电话
     */
    @ApiModelProperty(value = "紧急联系人电话")
    private String emergencyContactPhone;

    /**
     * QQ
     */
    @ApiModelProperty(value = "QQ")
    private String qq;

    /**
     * 微信
     */
    @ApiModelProperty(value = "微信")
    private String wechat;

    /**
     * 个人邮箱
     */
    @ApiModelProperty(value = "个人邮箱")
    private String personalEmail;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String remark;

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
     * 户口类型
     */
    @ApiModelProperty(value = "户口类型")

    private Long accountTypeId;
    /**
     * 最高学历
     */
    @ApiModelProperty(value = "最高学历")

    private Long highestEducationId;
    /**
     * 政治面貌
     */
    @ApiModelProperty(value = "政治面貌")

    private Long politicsStatusId;
    /**
     * 婚姻状况
     */
    @ApiModelProperty(value = "婚姻状况")

    private Long maritalStatusId;

    private Long empId;

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

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAccountLoc() {
        return accountLoc;
    }

    public void setAccountLoc(String accountLoc) {
        this.accountLoc = accountLoc;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getCurrentAddr() {
        return currentAddr;
    }

    public void setCurrentAddr(String currentAddr) {
        this.currentAddr = currentAddr;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long enumAccountTypeId) {
        this.accountTypeId = enumAccountTypeId;
    }

    public Long getHighestEducationId() {
        return highestEducationId;
    }

    public void setHighestEducationId(Long enumHighestEducationId) {
        this.highestEducationId = enumHighestEducationId;
    }

    public Long getPoliticsStatusId() {
        return politicsStatusId;
    }

    public void setPoliticsStatusId(Long enumPoliticsStatusId) {
        this.politicsStatusId = enumPoliticsStatusId;
    }

    public Long getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Long enumMaritalStatusId) {
        this.maritalStatusId = enumMaritalStatusId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long employeeId) {
        this.empId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonalDTO personalDTO = (PersonalDTO) o;
        if (personalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonalDTO{" +
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
            ", accountType=" + getAccountTypeId() +
            ", highestEducation=" + getHighestEducationId() +
            ", politicsStatus=" + getPoliticsStatusId() +
            ", maritalStatus=" + getMaritalStatusId() +
            ", emp=" + getEmpId() +
            "}";
    }
}
