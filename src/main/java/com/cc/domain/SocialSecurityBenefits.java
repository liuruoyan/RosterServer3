package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 社保福利信息 (多对一 员工)
 */
@Entity
@Table(name = "social_security_benefits")
public class SocialSecurityBenefits implements Serializable {

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
     * 公积金账号
     */
    @Column(name = "pf_account")
    private String pfAccount;

    /**
     * 补充公积金账号
     */
    @Column(name = "spf_account")
    private String spfAccount;

    /**
     * 公积金起始月
     */
    @Column(name = "pf_start_month")
    private LocalDate pfStartMonth;

    /**
     * 公积金基数
     */
    @Column(name = "pf_base")
    private Integer pfBase;

    /**
     * 公积金停缴月
     */
    @Column(name = "pf_stop_month")
    private LocalDate pfStopMonth;

    /**
     * 公积金备注
     */
    @Column(name = "pf_remark")
    private String pfRemark;

    /**
     * 社保账号
     */
    @Column(name = "ss_account")
    private String ssAccount;

    /**
     * 社保城市
     */
    @Column(name = "ss_city")
    private String ssCity;

    /**
     * 社保起始月
     */
    @Column(name = "ss_start_month")
    private LocalDate ssStartMonth;

    /**
     * 社保基数
     */
    @Column(name = "ss_base")
    private Integer ssBase;

    /**
     * 社保停缴月
     */
    @Column(name = "ss_stop_month")
    private LocalDate ssStopMonth;

    /**
     * 社保备注
     */
    @Column(name = "ss_remark")
    private String ssRemark;

    /**
     * 当年已免税额
     */
    @Column(name = "allowance", precision = 21, scale = 2)
    private BigDecimal allowance;

    /**
     * 个税缴款义务人
     */
    @Column(name = "taxpayer")
    private String taxpayer;

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
     * 公积金类型
     */
    @ManyToOne
    @JsonIgnoreProperties("socialSecurityBenefits")
    private EnumPfType pfType;

    /**
     * 公积金状态
     */
    @ManyToOne
    @JsonIgnoreProperties("socialSecurityBenefits")
    private EnumPfStatus pfStatus;

    /**
     * 公积金缴纳方案
     */
    @ManyToOne
    @JsonIgnoreProperties("socialSecurityBenefits")
    private EnumPfPayScheme providentPayScheme;

    /**
     * 社保缴纳方案
     */
    @ManyToOne
    @JsonIgnoreProperties("socialSecurityBenefits")
    private EnumSsPayScheme socialSecurityPayScheme;

    /**
     * 社保状态
     */
    @ManyToOne
    @JsonIgnoreProperties("socialSecurityBenefits")
    private EnumSsStatus ssStatus;

    /**
     * 工时类型
     */
    @ManyToOne
    @JsonIgnoreProperties("socialSecurityBenefits")
    private EnumEmpLaborType laborType;

    /**
     * 纳税人身份
     */
    @ManyToOne
    @JsonIgnoreProperties("socialSecurityBenefits")
    private EnumEmpTaxerType taxerType;

    /**
     * 纳税地区
     */
    @ManyToOne
    @JsonIgnoreProperties("socialSecurityBenefits")
    private EnumEmpTaxArea taxArea;

    @ManyToOne
    @JsonIgnoreProperties("socialSecurityBenefits")
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

    public SocialSecurityBenefits code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPfAccount() {
        return pfAccount;
    }

    public SocialSecurityBenefits pfAccount(String pfAccount) {
        this.pfAccount = pfAccount;
        return this;
    }

    public void setPfAccount(String pfAccount) {
        this.pfAccount = pfAccount;
    }

    public String getSpfAccount() {
        return spfAccount;
    }

    public SocialSecurityBenefits spfAccount(String spfAccount) {
        this.spfAccount = spfAccount;
        return this;
    }

    public void setSpfAccount(String spfAccount) {
        this.spfAccount = spfAccount;
    }

    public LocalDate getPfStartMonth() {
        return pfStartMonth;
    }

    public SocialSecurityBenefits pfStartMonth(LocalDate pfStartMonth) {
        this.pfStartMonth = pfStartMonth;
        return this;
    }

    public void setPfStartMonth(LocalDate pfStartMonth) {
        this.pfStartMonth = pfStartMonth;
    }

    public Integer getPfBase() {
        return pfBase;
    }

    public SocialSecurityBenefits pfBase(Integer pfBase) {
        this.pfBase = pfBase;
        return this;
    }

    public void setPfBase(Integer pfBase) {
        this.pfBase = pfBase;
    }

    public LocalDate getPfStopMonth() {
        return pfStopMonth;
    }

    public SocialSecurityBenefits pfStopMonth(LocalDate pfStopMonth) {
        this.pfStopMonth = pfStopMonth;
        return this;
    }

    public void setPfStopMonth(LocalDate pfStopMonth) {
        this.pfStopMonth = pfStopMonth;
    }

    public String getPfRemark() {
        return pfRemark;
    }

    public SocialSecurityBenefits pfRemark(String pfRemark) {
        this.pfRemark = pfRemark;
        return this;
    }

    public void setPfRemark(String pfRemark) {
        this.pfRemark = pfRemark;
    }

    public String getSsAccount() {
        return ssAccount;
    }

    public SocialSecurityBenefits ssAccount(String ssAccount) {
        this.ssAccount = ssAccount;
        return this;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public String getSsCity() {
        return ssCity;
    }

    public SocialSecurityBenefits ssCity(String ssCity) {
        this.ssCity = ssCity;
        return this;
    }

    public void setSsCity(String ssCity) {
        this.ssCity = ssCity;
    }

    public LocalDate getSsStartMonth() {
        return ssStartMonth;
    }

    public SocialSecurityBenefits ssStartMonth(LocalDate ssStartMonth) {
        this.ssStartMonth = ssStartMonth;
        return this;
    }

    public void setSsStartMonth(LocalDate ssStartMonth) {
        this.ssStartMonth = ssStartMonth;
    }

    public Integer getSsBase() {
        return ssBase;
    }

    public SocialSecurityBenefits ssBase(Integer ssBase) {
        this.ssBase = ssBase;
        return this;
    }

    public void setSsBase(Integer ssBase) {
        this.ssBase = ssBase;
    }

    public LocalDate getSsStopMonth() {
        return ssStopMonth;
    }

    public SocialSecurityBenefits ssStopMonth(LocalDate ssStopMonth) {
        this.ssStopMonth = ssStopMonth;
        return this;
    }

    public void setSsStopMonth(LocalDate ssStopMonth) {
        this.ssStopMonth = ssStopMonth;
    }

    public String getSsRemark() {
        return ssRemark;
    }

    public SocialSecurityBenefits ssRemark(String ssRemark) {
        this.ssRemark = ssRemark;
        return this;
    }

    public void setSsRemark(String ssRemark) {
        this.ssRemark = ssRemark;
    }

    public BigDecimal getAllowance() {
        return allowance;
    }

    public SocialSecurityBenefits allowance(BigDecimal allowance) {
        this.allowance = allowance;
        return this;
    }

    public void setAllowance(BigDecimal allowance) {
        this.allowance = allowance;
    }

    public String getTaxpayer() {
        return taxpayer;
    }

    public SocialSecurityBenefits taxpayer(String taxpayer) {
        this.taxpayer = taxpayer;
        return this;
    }

    public void setTaxpayer(String taxpayer) {
        this.taxpayer = taxpayer;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public SocialSecurityBenefits isSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
        return this;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public SocialSecurityBenefits isHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
        return this;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public EnumPfType getPfType() {
        return pfType;
    }

    public SocialSecurityBenefits pfType(EnumPfType enumPfType) {
        this.pfType = enumPfType;
        return this;
    }

    public void setPfType(EnumPfType enumPfType) {
        this.pfType = enumPfType;
    }

    public EnumPfStatus getPfStatus() {
        return pfStatus;
    }

    public SocialSecurityBenefits pfStatus(EnumPfStatus enumPfStatus) {
        this.pfStatus = enumPfStatus;
        return this;
    }

    public void setPfStatus(EnumPfStatus enumPfStatus) {
        this.pfStatus = enumPfStatus;
    }

    public EnumPfPayScheme getProvidentPayScheme() {
        return providentPayScheme;
    }

    public SocialSecurityBenefits providentPayScheme(EnumPfPayScheme enumPfPayScheme) {
        this.providentPayScheme = enumPfPayScheme;
        return this;
    }

    public void setProvidentPayScheme(EnumPfPayScheme enumPfPayScheme) {
        this.providentPayScheme = enumPfPayScheme;
    }

    public EnumSsPayScheme getSocialSecurityPayScheme() {
        return socialSecurityPayScheme;
    }

    public SocialSecurityBenefits socialSecurityPayScheme(EnumSsPayScheme enumSsPayScheme) {
        this.socialSecurityPayScheme = enumSsPayScheme;
        return this;
    }

    public void setSocialSecurityPayScheme(EnumSsPayScheme enumSsPayScheme) {
        this.socialSecurityPayScheme = enumSsPayScheme;
    }

    public EnumSsStatus getSsStatus() {
        return ssStatus;
    }

    public SocialSecurityBenefits ssStatus(EnumSsStatus enumSsStatus) {
        this.ssStatus = enumSsStatus;
        return this;
    }

    public void setSsStatus(EnumSsStatus enumSsStatus) {
        this.ssStatus = enumSsStatus;
    }

    public EnumEmpLaborType getLaborType() {
        return laborType;
    }

    public SocialSecurityBenefits laborType(EnumEmpLaborType enumEmpLaborType) {
        this.laborType = enumEmpLaborType;
        return this;
    }

    public void setLaborType(EnumEmpLaborType enumEmpLaborType) {
        this.laborType = enumEmpLaborType;
    }

    public EnumEmpTaxerType getTaxerType() {
        return taxerType;
    }

    public SocialSecurityBenefits taxerType(EnumEmpTaxerType enumEmpTaxerType) {
        this.taxerType = enumEmpTaxerType;
        return this;
    }

    public void setTaxerType(EnumEmpTaxerType enumEmpTaxerType) {
        this.taxerType = enumEmpTaxerType;
    }

    public EnumEmpTaxArea getTaxArea() {
        return taxArea;
    }

    public SocialSecurityBenefits taxArea(EnumEmpTaxArea enumEmpTaxArea) {
        this.taxArea = enumEmpTaxArea;
        return this;
    }

    public void setTaxArea(EnumEmpTaxArea enumEmpTaxArea) {
        this.taxArea = enumEmpTaxArea;
    }

    public Employee getEmp() {
        return emp;
    }

    public SocialSecurityBenefits emp(Employee employee) {
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
        if (!(o instanceof SocialSecurityBenefits)) {
            return false;
        }
        return id != null && id.equals(((SocialSecurityBenefits) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SocialSecurityBenefits{" +
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
            "}";
    }
}
