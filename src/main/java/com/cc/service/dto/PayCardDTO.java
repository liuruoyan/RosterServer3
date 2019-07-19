package com.cc.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cc.domain.PayCard} entity.
 */
@ApiModel(description = "工资卡 (多对一 员工)")
public class PayCardDTO implements Serializable {

    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 分支行
     */
    @ApiModelProperty(value = "分支行")
    private String branch;

    /**
     * 开户名
     */
    @ApiModelProperty(value = "开户名")
    private String accountName;

    /**
     * 银行账号
     */
    @ApiModelProperty(value = "银行账号")
    private String bankAccount;

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
     * 开户银行
     */
    @ApiModelProperty(value = "开户银行")

    private Long depositBankId;

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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
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

    public Long getDepositBankId() {
        return depositBankId;
    }

    public void setDepositBankId(Long enumDepositBankId) {
        this.depositBankId = enumDepositBankId;
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

        PayCardDTO payCardDTO = (PayCardDTO) o;
        if (payCardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), payCardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PayCardDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", branch='" + getBranch() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", bankAccount='" + getBankAccount() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            ", depositBank=" + getDepositBankId() +
            ", emp=" + getEmpId() +
            "}";
    }
}
