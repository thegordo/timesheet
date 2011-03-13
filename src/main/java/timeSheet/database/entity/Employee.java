package timeSheet.database.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: John Lawrence
 * Date: 12/10/10
 * Time: 1:02 AM
 */
@Entity
@SequenceGenerator(name="employeeGen", allocationSize = 1)
public class Employee extends BaseObject {
    public static final int STRING_LENGTH = 256;

    @Column(length = STRING_LENGTH)
    private String name;

    @Column(length = STRING_LENGTH, unique = true)
    private String userName;

    @Column(length = STRING_LENGTH)
    private String password;

    @Column(length = STRING_LENGTH)
    private String emailAddress;

    @Column(length = STRING_LENGTH)
    private String fileNumber;

    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @Temporal(TemporalType.DATE)
    private Date fullTimeDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeGen")
    protected int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = chopLength(name, STRING_LENGTH);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = chopLength(userName, STRING_LENGTH);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = chopLength(password, STRING_LENGTH);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = chopLength(emailAddress, STRING_LENGTH);
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = chopLength(fileNumber, STRING_LENGTH);
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        checkDates(hireDate, fullTimeDate);
        this.hireDate = hireDate;
    }

    public Date getFullTimeDate() {
        return fullTimeDate;
    }

    public void setFullTimeDate(Date fullTimeDate) {
        checkDates(hireDate, fullTimeDate);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Employee employee = (Employee) o;

        return userName.equals(employee.userName);

    }

    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    public enum Field {
        id,
        name,
        dateHired,
        fullTimeDate,
        group,
        role,
        userName,
        pass1,
        pass2,
        email,
        fileNum,
        active,
        ptoAllowed,
        salaried,
        wage,
    }

    private void checkDates(Date hireDate, Date fullTimeDate) {
        if (fullTimeDate != null && hireDate != null) {
            if (fullTimeDate.before(hireDate)) {
                throw new IllegalArgumentException("The full time date cannot be before the hire date.");
            }
        }
    }
}
