package timeSheet.database.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.*;

/**
 * User: John Lawrence
 * Date: 3/13/11
 * Time: 1:40 AM
 */
public class EmployeeGroupTest {
    private EmployeeGroup group;

    @Before
    public void setup() {
        group = new EmployeeGroup();
    }

    @Test
    public void testSetEmployeeList() throws Exception {
        assertEquals(0, group.getEmployeeList().size());
        Employee emp = new Employee();
        group.addEmployee(emp);
        assertEquals(1, group.getEmployeeList().size());
        group.setEmployeeList(new ArrayList<Employee>());
        assertEquals(0, group.getEmployeeList().size());
    }

    @Test
    public void testSetName() throws Exception {
        assertNull(group.getName());
        String name = "fred";
        group.setName(name);
        assertEquals(name, group.getName());
        name = UtilJUnit.getStringOfLength(EmployeeGroup.STRING_LENGTH + 1);
        group.setName(name);
        assertEquals(EmployeeGroup.STRING_LENGTH, group.getName().length());
    }

    @Test
    public void testSetId() throws Exception {
        assertEquals(0, group.getId());
        group.setId(1);
        assertEquals(1, group.getId());
    }

    @Test
    public void testEquals() throws Exception {
        int id = 1;
        String name = "name";
        group.setId(id);
        group.setName(name);
        EmployeeGroup group2 = new EmployeeGroup();
        group2.setId(id);
        group2.setName(name);
        assertEquals(group, group2);
        group2.setId(2);
        assertFalse(group.equals(group2));
        group2.setId(id);
        assertEquals(group, group2);
        group2.setName("fred");
        assertFalse(group.equals(group2));
        //noinspection ObjectEqualsNull
        assertFalse(group.equals(null));
        //noinspection EqualsBetweenInconvertibleTypes
        assertFalse(group.equals(""));
    }

    @Test
    public void testHashCode() throws Exception {
        int id = 1;
        String name = "name";
        group.setId(id);
        group.setName(name);
        EmployeeGroup group2 = new EmployeeGroup();
        group2.setId(id);
        group2.setName(name);
        assertEquals(group.hashCode(), group2.hashCode());
        group2.setId(2);
        assertNotSame(group.hashCode(), group2.hashCode());
        group2.setId(id);
        assertEquals(group.hashCode(), group2.hashCode());
        group2.setName("fred");
        assertNotSame(group.hashCode(), group2.hashCode());
    }


    @Test
    public void testFieldEnum() throws Exception {
        assertEquals(2, EmployeeGroup.Field.values().length);
    }
}
