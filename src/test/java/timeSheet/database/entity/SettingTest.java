package timeSheet.database.entity;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * User: John Lawrence
 * Date: 3/13/11
 * Time: 5:54 PM
 */
public class SettingTest {
    private Setting setting;

    @Before
    public void setUp() throws Exception {
        setting = new Setting();
    }

    @Test
    public void testSetName() throws Exception {
        assertNull(setting.getName());
        String name = "fred";
        setting.setName(name);
        assertEquals(name, setting.getName());
        name = UtilJUnit.getStringOfLength(Setting.STRING_LENGTH + 1);
        setting.setName(name);
        assertEquals(Setting.STRING_LENGTH, setting.getName().length());
    }

    @Test
    public void testSetValue() throws Exception {
        assertNull(setting.getValue());
        String name = "fred";
        setting.setValue(name);
        assertEquals(name, setting.getValue());
        name = UtilJUnit.getStringOfLength(Setting.STRING_LENGTH + 1);
        setting.setValue(name);
        assertEquals(Setting.STRING_LENGTH, setting.getValue().length());
    }

    @Test
    public void testSetId() throws Exception {
        assertEquals(0, setting.getId());
        setting.setId(1);
        assertEquals(1, setting.getId());
    }
}
