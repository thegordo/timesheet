package timeSheet.database.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: John Lawrence
 * Date: 2/28/11
 * Time: 9:42 PM
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findHoursByEmployeeAndDate", query = "Select c from Hours c where c.employee = :employee and c.date = :date"),
        @NamedQuery(name = "findSumOfPaidHours", query = "Select sum(c.hours) from Hours c where c.date between :start and :end and c.employee = :employee and c.type.paid = true")
})
@SequenceGenerator(name = "hoursGen", allocationSize = 1)
public class Hours extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hoursGen")
    private int id;

    @ManyToOne(targetEntity = HourType.class, cascade = CascadeType.PERSIST)
    private HourType type;

    @ManyToOne(targetEntity = Employee.class, cascade = CascadeType.PERSIST)
    private Employee employee;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.DATE)
    private Date dateEntered;

    @Column
    private Double hours;

    @ManyToOne(targetEntity = Employee.class, cascade = CascadeType.PERSIST)
    private Employee enteredByEmployee;

    @Column
    private boolean employeeApproval;

    @Column
    private boolean managerApproval;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HourType getType() {
        return type;
    }

    public void setType(HourType type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Employee getEnteredByEmployee() {
        return enteredByEmployee;
    }

    public void setEnteredByEmployee(Employee enteredByEmployee) {
        this.enteredByEmployee = enteredByEmployee;
    }

    public boolean getEmployeeApproval() {
        return employeeApproval;
    }

    public void setEmployeeApproval(boolean employeeApproval) {
        this.employeeApproval = employeeApproval;
    }

    public boolean getManagerApproval() {
        return managerApproval;
    }

    public void setManagerApproval(boolean managerApproval) {
        this.managerApproval = managerApproval;
    }

    public boolean isEnteredByEmployee() {
        return employee != null && enteredByEmployee != null && employee.equals(enteredByEmployee);
    }

    public void validate() {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        if (enteredByEmployee == null) {
            throw new IllegalArgumentException("Entered by Employee cannot be null.");
        }
        if (hours == null || hours == 0) {
            throw new IllegalArgumentException("Cannot save hours with no hours to save.");
        }
    }
}
