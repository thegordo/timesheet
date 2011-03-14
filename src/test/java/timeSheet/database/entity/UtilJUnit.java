package timeSheet.database.entity;

/**
 * User: John Lawrence
 * Date: 3/13/11
 * Time: 12:58 AM
 */
public class UtilJUnit {
    public static String getStringOfLength(int length) {
        StringBuilder build = new StringBuilder();
        for(int index = 0; index < length; index ++) {
            build.append('a');
        }
        return build.toString();
    }
}
