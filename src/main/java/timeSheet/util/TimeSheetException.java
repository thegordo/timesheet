package timeSheet.util;

/**
 * User: John Lawrence
 * Date: 1/20/11
 * Time: 5:50 AM
 */
public class TimeSheetException extends Exception {
    public TimeSheetException() {
    }

    public TimeSheetException(String message) {
        super(message);
    }

    public TimeSheetException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeSheetException(Throwable cause) {
        super(cause);
    }
}
