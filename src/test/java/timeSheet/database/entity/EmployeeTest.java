package timeSheet.database.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.*;

/**
 * User: John Lawrence
 * Date: 3/13/11
 * Time: 12:38 AM
 */
public class EmployeeTest {
    private Employee employee;
    @Before
    public void setup() {
        employee = new Employee();
    }

    @Test
    public void testSetName() throws Exception {
        assertNull(employee.getName());
        String name = "fred";
        employee.setName(name);
        assertEquals(name, employee.getName());
        name = UtilJUnit.getStringOfLength(Employee.STRING_LENGTH + 1);
        employee.setName(name);
        assertEquals(Employee.STRING_LENGTH, employee.getName().length());
    }

    @Test
    public void testSetUserName() throws Exception {
        assertNull(employee.getUserName());
        String name = "fred";
        employee.setUserName(name);
        assertEquals(name, employee.getUserName());
        name = UtilJUnit.getStringOfLength(Employee.STRING_LENGTH + 1);
        employee.setName(name);
        assertEquals(Employee.STRING_LENGTH, employee.getName().length());
    }

    @Test
    public void testSetPassword() throws Exception {
        assertNull(employee.getPassword());
        String name = "fred";
        employee.setPassword(name);
        assertEquals(name, employee.getPassword());
        name = UtilJUnit.getStringOfLength(Employee.STRING_LENGTH + 1);
        employee.setName(name);
        assertEquals(Employee.STRING_LENGTH, employee.getName().length());
    }

    @Test
    public void testSetEmailAddress() throws Exception {
        assertNull(employee.getEmailAddress());
        String name = "fred";
        employee.setEmailAddress(name);
        assertEquals(name, employee.getEmailAddress());
        name = UtilJUnit.getStringOfLength(Employee.STRING_LENGTH + 1);
        employee.setName(name);
        assertEquals(Employee.STRING_LENGTH, employee.getName().length());
    }

    @Test
    public void testSetFileNumber() throws Exception {
        assertNull(employee.getFileNumber());
        String name = "fred";
        employee.setFileNumber(name);
        assertEquals(name, employee.getFileNumber());
        name = UtilJUnit.getStringOfLength(Employee.STRING_LENGTH + 1);
        employee.setName(name);
        assertEquals(Employee.STRING_LENGTH, employee.getName().length());
    }

    @Test
    public void testSetHireDate() throws Exception {
        Date date = new Date();
        assertNull(employee.getHireDate());
        employee.setHireDate(date);
        assertEquals(date, employee.getHireDate());
    }

    @Test
    public void testSetFullTimeDate() throws Exception {
        Date date = new Date();
        employee.setHireDate(date);
        assertNull(employee.getFullTimeDate());
        employee.setFullTimeDate(date);
        assertEquals(date, employee.getFullTimeDate());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetFullTimeDateBeforeHireDate() throws Exception {
        Calendar hireDate = Calendar.getInstance();
        hireDate.set(2010, 4, 5);
        Calendar fullTimeDate = Calendar.getInstance();
        fullTimeDate.set(2010, 3, 5);
        employee.setHireDate(hireDate.getTime());
        employee.setFullTimeDate(fullTimeDate.getTime());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetFullTimeDateBeforeHireDate2() throws Exception {
        Calendar hireDate = Calendar.getInstance();
        hireDate.set(2010, 4, 5);
        Calendar fullTimeDate = Calendar.getInstance();
        fullTimeDate.set(2010, 3, 5);
        employee.setFullTimeDate(fullTimeDate.getTime());
        employee.setHireDate(hireDate.getTime());
    }

    @Test
    public void testSetGroup() throws Exception {
        EmployeeGroup group = new EmployeeGroup();
        group.setName("name");
        group.setId(1);

        assertNull(employee.getGroup());
        employee.setGroup(group);
        assertEquals(group, employee.getGroup());
    }

    @Test
    public void testSetRole() throws Exception {
        assertNull(employee.getRole());
        employee.setRole(GroupRole.Employee);
        assertEquals(GroupRole.Employee, employee.getRole());
    }

    @Test
    public void testSetWage() throws Exception {
        Double wage = 2.22;
        assertNull(employee.getWage());
        employee.setWage(wage);
        assertEquals(wage, employee.getWage());
    }

    @Test
    public void testSetPtoAllowed() throws Exception {
        assertNull(employee.getPtoAllowed());
        employee.setPtoAllowed(false);
        assertFalse(employee.getPtoAllowed());
    }

    @Test
    public void testSetSalary() throws Exception {
        assertNull(employee.getSalary());
        employee.setSalary(false);
        assertFalse(employee.getSalary());
    }

    @Test
    public void testSetActiveFlag() throws Exception {
        assertNull(employee.getActiveFlag());
        employee.setActiveFlag(false);
        assertFalse(employee.getActiveFlag());
    }

    @Test
    public void testIsAdmin() throws Exception {
        for(GroupRole role : GroupRole.values()) {
            employee.setRole(role);
            switch (employee.getRole()) {
                case Administrator:
                    assertTrue(employee.isAdmin());
                    break;
                default:
                    assertFalse(employee.isAdmin());
                    break;
            }
        }
    }

    @Test
    public void testSetId() throws Exception {
        assertEquals(0, employee.getId());
        employee.setId(1);
        assertEquals(1, employee.getId());
    }

    @Test
    public void testEquals() throws Exception {
        Employee employee2 = new Employee();
        employee.setUserName("fred");
        employee2.setUserName("fred");
        assertTrue(employee.equals(employee2));
        employee2.setUserName("Fred");
        assertFalse(employee.equals(employee2));
        //noinspection ObjectEqualsNull
        assertFalse(employee.equals(null));
        //noinspection EqualsBetweenInconvertibleTypes
        assertFalse(employee.equals(""));

    }

    @Test
    public void testHashCode() throws Exception {
        Employee employee2 = new Employee();
        employee.setUserName("fred");
        employee2.setUserName("fred");
        assertEquals(employee.hashCode(), employee2.hashCode());
        employee2.setUserName("Fred");
        assertNotSame(employee.hashCode(), employee2.hashCode());
    }

    @Test
    public void testFieldEnum() throws Exception {
        assertEquals(15, Employee.Field.values().length);
    }
}
