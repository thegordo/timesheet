package timeSheet.util.properties;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * User: John Lawrence
 * Date: 12/31/10
 * Time: 11:45 PM
 */
public class EncryptedPropertiesTest {
    @Test
    public void testEncryptedProperties() throws Exception {
        File tempFile = new File("target/temp.properties");
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        EncryptedProperties properties = new EncryptedProperties();
        properties.setProperty("test", "testValue");
        properties.store(tempFile, "");
        EncryptedProperties prop2 = new EncryptedProperties();
        prop2.load(tempFile);
        assertEquals("testValue", prop2.getProperty("test"));
    }
}
