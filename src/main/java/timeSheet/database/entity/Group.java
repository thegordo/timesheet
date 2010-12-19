package timeSheet.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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

    @OneToMany
    List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
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
}
