package timeSheet.database.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.*;

/**
 * User: John Lawrence
 * Date: 3/13/11
 * Time: 9:19 AM
 */
public class HoursTest {
    private Hours hours;

    @Before
    public void setup() {
        hours = new Hours();
    }

    @Test
    public void testSetId() throws Exception {
        assertEquals(0, hours.getId());
        hours.setId(1);
        assertEquals(1, hours.getId());
    }

    @Test
    public void testSetType() throws Exception {
        assertNull(hours.getType());
        HourType type = new HourType();
        type.setId(3);
        type.setName("name");
        hours.setType(type);
        assertEquals(type, hours.getType());
    }

    @Test
    public void testSetEmployee() throws Exception {
        assertNull(hours.getEmployee());
        Employee employee = new Employee();
        employee.setUserName("name");
        hours.setEmployee(employee);
        assertEquals(employee, hours.getEmployee());
    }

    @Test
    public void testSetDate() throws Exception {
        Date date = new Date();
        assertNull(hours.getDate());
        hours.setDate(date);
        assertEquals(date, hours.getDate());
    }

    @Test
    public void testSetDateEntered() throws Exception {
        Date date = new Date();
        assertNull(hours.getDateEntered());
        hours.setDateEntered(date);
        assertEquals(date, hours.getDateEntered());
    }

    @Test
    public void testSetHours() throws Exception {
        Double hoursWorked = 5.6;
        assertNull(hours.getHours());
        hours.setHours(hoursWorked);
        assertEquals(hoursWorked, hours.getHours());
    }

    @Test
    public void testSetEnteredByEmployee() throws Exception {
        assertNull(hours.getEnteredByEmployee());
        Employee employee = new Employee();
        employee.setUserName("name");
        hours.setEnteredByEmployee(employee);
        assertEquals(employee, hours.getEnteredByEmployee());
    }

    @Test
    public void testSetEmployeeApproval() throws Exception {
        assertFalse(hours.getEmployeeApproval());
        hours.setEmployeeApproval(true);
        assertTrue(hours.getEmployeeApproval());
    }

    @Test
    public void testSetManagerApproval() throws Exception {
        assertFalse(hours.getManagerApproval());
        hours.setManagerApproval(true);
        assertTrue(hours.getManagerApproval());
    }

    @Test
    public void testIsEnteredByEmployee() throws Exception {
        Employee emp1 = new Employee();
        emp1.setUserName("name");
        hours.setEmployee(emp1);
        hours.setEnteredByEmployee(emp1);
        assertTrue(hours.isEnteredByEmployee());
        Employee emp2 = new Employee();
        emp2.setUserName("fred");
        hours.setEnteredByEmployee(emp2);
        assertFalse(hours.isEnteredByEmployee());
    }
}
