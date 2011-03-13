package timeSheet.database.entity;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: John Lawrence
 * Date: 3/13/11
 * Time: 12:52 AM
 */
public class BaseObjectTest {
    @Test
    public void testName() throws Exception {
        BaseObject obj = new BaseObject() {
            @Override public int getId() { return 0; }
            @Override public void setId(int id) { }
        };
        String string = "fred";
        assertEquals("fred", obj.chopLength(string, 5));
        assertEquals("fred", obj.chopLength(string, 4));
        assertEquals("fre", obj.chopLength(string, 3));
    }
}
