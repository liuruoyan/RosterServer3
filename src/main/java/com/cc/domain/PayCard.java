package com.cc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 工资卡 (多对一 员工)
 */
@Entity
@Table(name = "pay_card")
public class PayCard implements Serializable {

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
     * 分支行
     */
    @Column(name = "branch")
    private String branch;

    /**
     * 开户名
     */
    @Column(name = "account_name")
    private String accountName;

    /**
     * 银行账号
     */
    @Column(name = "bank_account")
    private String bankAccount;

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
     * 开户银行
     */
    @ManyToOne
    @JsonIgnoreProperties("payCards")
    private EnumDepositBank depositBank;

    @ManyToOne
    @JsonIgnoreProperties("payCards")
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

    public PayCard code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBranch() {
        return branch;
    }

    public PayCard branch(String branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountName() {
        return accountName;
    }

    public PayCard accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public PayCard bankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Boolean isIsSelfVerify() {
        return isSelfVerify;
    }

    public PayCard isSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
        return this;
    }

    public void setIsSelfVerify(Boolean isSelfVerify) {
        this.isSelfVerify = isSelfVerify;
    }

    public Boolean isIsHrVerify() {
        return isHrVerify;
    }

    public PayCard isHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
        return this;
    }

    public void setIsHrVerify(Boolean isHrVerify) {
        this.isHrVerify = isHrVerify;
    }

    public EnumDepositBank getDepositBank() {
        return depositBank;
    }

    public PayCard depositBank(EnumDepositBank enumDepositBank) {
        this.depositBank = enumDepositBank;
        return this;
    }

    public void setDepositBank(EnumDepositBank enumDepositBank) {
        this.depositBank = enumDepositBank;
    }

    public Employee getEmp() {
        return emp;
    }

    public PayCard emp(Employee employee) {
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
        if (!(o instanceof PayCard)) {
            return false;
        }
        return id != null && id.equals(((PayCard) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PayCard{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", branch='" + getBranch() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", bankAccount='" + getBankAccount() + "'" +
            ", isSelfVerify='" + isIsSelfVerify() + "'" +
            ", isHrVerify='" + isIsHrVerify() + "'" +
            "}";
    }
}
