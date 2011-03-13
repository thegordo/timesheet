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
@SequenceGenerator(name = "groupGen", allocationSize = 1)
public class EmployeeGroup extends BaseObject {
    public static final int STRING_LENGTH = 256;

    @Column(length = STRING_LENGTH, unique = true)
    public String name;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "group")
    List<Employee> employeeList;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupGen")
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
        this.name = chopLength(name, STRING_LENGTH);
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeGroup group = (EmployeeGroup) o;
        return id == group.id && name.equals(group.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id;
        return result;
    }

    public enum Field {
        id,
        name,
    }
}
