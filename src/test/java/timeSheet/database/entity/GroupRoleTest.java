package timeSheet.database.entity;

import org.junit.Test;

import java.util.Scanner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * User: John Lawrence
 * Date: 3/13/11
 * Time: 9:09 AM
 */
public class GroupRoleTest {
    @Test
    public void testIsTimeManager() throws Exception {
        for (GroupRole role : GroupRole.values()) {
            switch (role) {
                case Employee:
                    assertFalse(role.isTimeManager());
                    break;
                default:
                    assertTrue(role.isTimeManager());
                    break;
            }
        }
    }

    @Test
    public void testGetOptions() throws Exception {
        String options = GroupRole.getHtmlOptions(null);
        for (GroupRole role : GroupRole.values()) {
            assertTrue(options.contains(role.toDisplayString()));
        }
        options = GroupRole.getHtmlOptions(GroupRole.Administrator);
        Scanner scanMe = new Scanner(options);
        while (scanMe.hasNextLine()) {
            String line = scanMe.nextLine();
            if (line.contains("selected")) {
                assertTrue(line.contains(GroupRole.Administrator.toDisplayString()));
                break;
            }
        }
    }
}
