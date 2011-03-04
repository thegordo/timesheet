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
@NamedQueries({
        @NamedQuery(name = "findAllGroups", query = "SELECT c FROM EmployeeGroup c"),
        @NamedQuery(name = "findGroupsById", query = "SELECT c FROM EmployeeGroup c WHERE c.id = :id"),
        @NamedQuery(name = "findGroupsByName", query = "SELECT c FROM EmployeeGroup c WHERE UPPER(c.name) = UPPER(:name)")
})
public class EmployeeGroup extends BaseObject {
    @Column(length = 256, unique = true)
    public String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "group")
    List<Employee> employeeList;

    @Id
    @GeneratedValue
    protected int id;

    public List<Employee> getEmployeeList() {
        if (employeeList == null) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum Field {
        id,
        name,
    }
}
