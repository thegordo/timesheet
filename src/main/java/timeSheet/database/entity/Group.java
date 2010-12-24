package timeSheet.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: John Lawrence
 * Date: 12/10/10
 * Time: 1:06 AM
 */
@Entity
public class Group extends BaseObject {
    @Column(length = 256)
    public String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "group")
    List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        if(employeeList == null) {
            employeeList = new ArrayList<Employee>();
        }
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEmployee(Employee emp) {
        getEmployeeList().add(emp);
    }
}
