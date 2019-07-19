package com.cc.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.cc.domain.SocialSecurityBenefits} entity.
 */
@ApiModel(description = "社保福利信息 (多对一 员工)")
public class SocialSecurityBenefitsDTO implements Serializable {

    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 公积金账号
     */
    @ApiModelProperty(value = "公积金账号")
    private String pfAccount;

    /**
     * 补充公积金账号
     */
    @ApiModelProperty(value = "补充公积金账号")
    private String spfAccount;

    /**
     * 公积金起始月
     */
    @ApiModelProperty(value = "公积金起始月")
    private LocalDate pfStartMonth;

    /**
     * 公积金基数
     */
    @ApiModelProperty(value = "公积金基数")
    private Integer pfBase;

    /**
     * 公积金停缴月
     */
    @ApiModelProperty(value = "公积金停缴月")
    private LocalDate pfStopMonth;

    /**
     * 公积金备注
     */
    @ApiModelProperty(value = "公积金备注")
    private String pfRemark;

    /**
     * 社保账号
     */
    @ApiModelProperty(value = "社保账号")
    private String ssAccount;

    /**
     * 社保城市
     */
    @ApiModelProperty(value = "社保城市")
    private String ssCity;

    /**
     * 社保起始月
     */
    @ApiModelProperty(value = "社保起始月")
    private LocalDate ssStartMonth;

    /**
     * 社保基数
     */
    @ApiModelProperty(value = "社保基数")
    private Integer ssBase;

    /**
     * 社保停缴月
     */
    @ApiModelProperty(value = "社保停缴月")
    private LocalDate ssStopMonth;

    /**
     * 社保备注
     */
    @ApiModelProperty(value = "社保备注")
    private String ssRemark;

    /**
     * 当年已免税额
     */
    @ApiModelProperty(value = "当年已免税额")
    private BigDecimal allowance;

    /**
     * 个税缴款义务人
     */
    @ApiModelProperty(value = "个税缴款义务人")
    private String taxpayer;

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
     * 公积金类型
     */
    @ApiModelProperty(value = "公积金类型")

    private Long pfTypeId;
    /**
     * 公积金状态
     */
    @ApiModelProperty(value = "公积金状态")

    private Long pfStatusId;
    /**
     * 公积金缴纳方案
     */
    @ApiModelProperty(value = "公积金缴纳方案")

    private Long providentPaySchemeId;
    /**
     * 社保缴纳方案
     */
    @ApiModelProperty(value = "社保缴纳方案")

    private Long socialSecurityPaySchemeId;
    /**
     * 社保状态
     */
    @ApiModelProperty(value = "社保状态")

    private Long ssStatusId;
    /**
     * 工时类型
     */
    @ApiModelProperty(value = "工时类型")

    private Long laborTypeId;
    /**
     * 纳税人身份
     */
    @ApiModelProperty(value = "纳税人身份")

    private Long taxerTypeId;
    /**
     * 纳税地区
     */
    @ApiModelProperty(value = "纳税地区")

    private Long taxAreaId;

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

    public String getPfAccount() {
        return pfAccount;
    }

    public void setPfAccount(String pfAccount) {
        this.pfAccount = pfAccount;
    }

    public String getSpfAccount() {
        return spfAccount;
    }

    public void setSpfAccount(String spfAccount) {
        this.spfAccount = spfAccount;
    }

    public LocalDate getPfStartMonth() {
        return pfStartMonth;
    }

    public void setPfStartMonth(LocalDate pfStartMonth) {
        this.pfStartMonth = pfStartMonth;
    }

    public Integer getPfBase() {
        return pfBase;
    }

    public void setPfBase(Integer pfBase) {
        this.pfBase = pfBase;
    }

    public LocalDate getPfStopMonth() {
        return pfStopMonth;
    }

    public void setPfStopMonth(LocalDate pfStopMonth) {
        this.pfStopMonth = pfStopMonth;
    }

    public String getPfRemark() {
        return pfRemark;
    }

    public void setPfRemark(String pfRemark) {
        this.pfRemark = pfRemark;
    }

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public String getSsCity() {
        return ssCity;
    }

    public void setSsCity(String ssCity) {
        this.ssCity = ssCity;
    }

    public LocalDate getSsStartMonth() {
        return ssStartMonth;
    }

    public void setSsStartMonth(LocalDate ssStartMonth) {
        this.ssStartMonth = ssStartMonth;
    }

    public Integer getSsBase() {
        return ssBase;
    }

    public void setSsBase(Integer ssBase) {
        this.ssBase = ssBase;
    }

    public LocalDate getSsStopMonth() {
        return ssStopMonth;
    }

    public void setSsStopMonth(LocalDate ssStopMonth) {
        this.ssStopMonth = ssStopMonth;
    }

    public String getSsRemark() {
        return ssRemark;
    }

    public void setSsRemark(String ssRemark) {
        this.ssRemark = ssRemark;
    }

    public BigDecimal getAllowance() {
        return allowance;
    }

    public void setAllowance(BigDecimal allowance) {
        this.allowance = allowance;
    }

    public String getTaxpayer() {
        return taxpayer;
    }

    public void setTaxpayer(String taxpayer) {
        this.taxpayer = taxpayer;
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

    public Long getPfTypeId() {
        return pfTypeId;
    }

    public void setPfTypeId(Long enumPfTypeId) {
        this.pfTypeId = enumPfTypeId;
    }

    public Long getPfStatusId() {
        return pfStatusId;
    }

    public void setPfStatusId(Long enumPfStatusId) {
        this.pfStatusId = enumPfStatusId;
    }

    public Long getProvidentPaySchemeId() {
        return providentPaySchemeId;
    }

    public void setProvidentPaySchemeId(Long enumPfPaySchemeId) {
        this.providentPaySchemeId = enumPfPaySchemeId;
    }

    public Long getSocialSecurityPaySchemeId() {
        return socialSecurityPaySchemeId;
    }

    public void setSocialSecurityPaySchemeId(Long enumSsPaySchemeId) {
        this.socialSecurityPaySchemeId = enumSsPaySchemeId;
    }

    public Long getSsStatusId() {
        return ssStatusId;
    }

    public void setSsStatusId(Long enumSsStatusId) {
        this.ssStatusId = enumSsStatusId;
    }

    public Long getLaborTypeId() {
        return laborTypeId;
    }

    public void setLaborTypeId(Long enumEmpLaborTypeId) {
        this.laborTypeId = enumEmpLaborTypeId;
    }

    public Long getTaxerTypeId() {
        return taxerTypeId;
    }

    public void setTaxerTypeId(Long enumEmpTaxerTypeId) {
        this.taxerTypeId = enumEmpTaxerTypeId;
    }

    public Long getTaxAreaId() {
        return taxAreaId;
    }

    public void setTaxAreaId(Long enumEmpTaxAreaId) {
        this.taxAreaId = enumEmpTaxAreaId;
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

        SocialSecurityBenefitsDTO socialSecurityBenefitsDTO = (SocialSecurityBenefitsDTO) o;
        if (socialSecurityBenefitsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), socialSecurityBenefitsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocialSecurityBenefitsDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", pfAccount='" + getPfAccount() + "'" +
            ", spfAccount='" + getSpfAccount() + "'" +
            ", pfStartMonth='" + getPfStartMonth() + "'" +
            ", pfBase=" + getPfBase() +
            ", pfStopMonth='" + getPfStopMonth() + "'" +
            ", pfRemark='" + getPfRemark() + "'" +
            ", ssAccount='" + getSsAccount() + "'" +
            ", ssCity='" + getSsCity() + "'" +
            ", ssStartMonth='" + getSsStartMonth() + "'" +
            ", ssBase=" + getSsBase() +
            ", ssStopMonth='" + getSsStopMonth() + "'" +
            ", ssRemark='" + getSsRemark() + "'" +
            ", allowance=" + getAllowance() +
            ", taxpayer='" + getTaxpayer() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            ", pfType=" + getPfTypeId() +
            ", pfStatus=" + getPfStatusId() +
            ", providentPayScheme=" + getProvidentPaySchemeId() +
            ", socialSecurityPayScheme=" + getSocialSecurityPaySchemeId() +
            ", ssStatus=" + getSsStatusId() +
            ", laborType=" + getLaborTypeId() +
            ", taxerType=" + getTaxerTypeId() +
            ", taxArea=" + getTaxAreaId() +
            ", emp=" + getEmpId() +
            "}";
    }
}
