package timeSheet.database.entity;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * User: John Lawrence
 * Date: 3/13/11
 * Time: 9:44 AM
 */
public class HourTypeTest {
    private HourType hourType;

    @Before
    public void setup() {
        hourType = new HourType();
    }

    @Test
    public void testSetName() throws Exception {
        assertNull(hourType.getName());
        String name = "name";
        hourType.setName(name);
        assertEquals(name, hourType.getName());
    }

    @Test
    public void testSetPaid() throws Exception {
        assertNull(hourType.getPaid());
        hourType.setPaid(true);
        assertTrue(hourType.getPaid());
    }

    @Test
    public void testSetDefaultFlag() throws Exception {
        assertNull(hourType.getDefaultFlag());
        hourType.setDefaultFlag(true);
        assertTrue(hourType.getDefaultFlag());
    }

    @Test
    public void testSetId() throws Exception {
        assertEquals(0, hourType.getId());
        hourType.setId(1);
        assertEquals(1, hourType.getId());
    }

    @Test
    public void testEquals() throws Exception {
        String name = "name";
        hourType.setName(name);
        HourType hourType2 = new HourType();
        hourType2.setName("fred");
        assertFalse(hourType.equals(hourType2));
        hourType2.setName(name);
        assertTrue(hourType.equals(hourType2));
        //noinspection ObjectEqualsNull
        assertFalse(hourType.equals(null));
        //noinspection EqualsBetweenInconvertibleTypes
        assertFalse(hourType.equals("fdr"));
    }

    @Test
    public void testHashCode() throws Exception {
        String name = "name";
        hourType.setName(name);
        HourType hourType2 = new HourType();
        hourType2.setName("fred");
        assertNotSame(hourType.hashCode(), hourType2.hashCode());
        hourType2.setName(name);
        assertEquals(hourType.hashCode(), hourType2.hashCode());
    }

    @Test
    public void testFieldEnum() throws Exception {
        assertEquals(4, HourType.Field.values().length);
    }
}
