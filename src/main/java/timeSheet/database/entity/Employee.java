package timeSheet.database.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: John Lawrence
 * Date: 12/10/10
 * Time: 1:02 AM
 */
@Entity
public class Employee extends BaseObject {
    @Column(length = 256)
    private String name;

    @Column(length = 256)
    private String userName;

    @Column(length = 256)
    private String password;

    @Column(length = 256)
    private String emailAddress;

    @Column(length = 256)
    private String fileNumber;

    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @Temporal(TemporalType.DATE)
    private Date fullTimeDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private EmployeeGroup group;

    @Enumerated(EnumType.STRING)
    @Column(length = 256)
    private GroupRole role;

    @Column
    private Double wage;

    @Column
    private Boolean ptoAllowed;

    @Column
    private Boolean isSalary;

    @Column
    private Boolean activeFlag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getFullTimeDate() {
        return fullTimeDate;
    }

    public void setFullTimeDate(Date fullTimeDate) {
        this.fullTimeDate = fullTimeDate;
    }

    public EmployeeGroup getGroup() {
        return group;
    }

    public void setGroup(EmployeeGroup group) {
        this.group = group;
    }

    public GroupRole getRole() {
        return role;
    }

    public void setRole(GroupRole role) {
        this.role = role;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public Boolean getPtoAllowed() {
        return ptoAllowed;
    }

    public void setPtoAllowed(Boolean ptoAllowed) {
        this.ptoAllowed = ptoAllowed;
    }

    public Boolean getSalary() {
        return isSalary;
    }

    public void setSalary(Boolean salary) {
        isSalary = salary;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public boolean isAdmin() {
        return role == GroupRole.Administrator;
    }
}
