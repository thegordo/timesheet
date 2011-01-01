package timeSheet.util.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * User: John Lawrence
 * Date: 12/31/10
 * Time: 9:35 PM
 */
public class EncryptedProperties {
    private HashMap<String, String> properties;
    private Base64Coder encoderDecoder;

    public EncryptedProperties() {
        properties = new HashMap<String, String>();
        encoderDecoder = new Base64Coder();
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    public String getProperty(String key, String defaultValue) {
        String val = getProperty(key);
        return (val == null) ? defaultValue : val;
    }

    public void store(File file, String comments) throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream(file));
        Scanner commentsWriter = new Scanner(comments);
        while (commentsWriter.hasNextLine()) {
            out.println("#" + commentsWriter.nextLine());
        }
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            String line = entry.getKey() + "=" + entry.getValue();
            line = encrypt(line);
            out.println(line);
        }
    }

    public void load(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = decrypt(line);
            processLine(line);
        }

    }

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    private String decrypt(String line) {
        return encoderDecoder.decodeString(line);
    }

    private String encrypt(String line) {
        return encoderDecoder.encodeString(line);
    }

    private void processLine(String line) {
        if (!line.startsWith("#")) {
            String[] pair = line.split("=");
            if (pair.length > 1) {
                properties.put(pair[0], pair[1]);
            }
        }
    }
}
