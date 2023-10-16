package com.codegym.backend.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "change_password_date", columnDefinition = "Date")
    private String changePassworDate;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "email")
    private String email;

    @Column(name = "enable_flag")
    private boolean enableFlag;

    @OneToMany(mappedBy = "account")
    private List<AccountRole> accountRoles;


    public Account(String userName, String password, String changePassworDate, String verificationCode, String email, boolean enableFlag) {
        this.userName = userName;
        this.password = password;
        this.changePassworDate = changePassworDate;
        this.verificationCode = verificationCode;
        this.email = email;
        this.enableFlag = enableFlag;
    }

    public Account(String userName, String password, String verificationCode, String email, boolean enableFlag) {
        this.userName = userName;
        this.password = password;
        this.verificationCode = verificationCode;
        this.email = email;
        this.enableFlag = enableFlag;
    }

    public Account(int id, String userName, String password, String verificationCode, String email, boolean enableFlag, List<AccountRole> accountRoles, String changePassworDate) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.verificationCode = verificationCode;
        this.email = email;
        this.enableFlag = enableFlag;
        this.accountRoles = accountRoles;
        this.changePassworDate = changePassworDate;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(boolean enableFlag) {
        this.enableFlag = enableFlag;
    }

    public List<AccountRole> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(List<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }

    public String getChangePassworDate() {
        return changePassworDate;
    }

    public void setChangePassworDate(String changePassworDate) {
        this.changePassworDate = changePassworDate;
    }
}