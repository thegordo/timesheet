package timeSheet;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * User: John Lawrence
 * Date: 2/27/11
 * Time: 11:19 AM
 */
public class PayPeriodTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSetUp() throws Exception {
        Calendar calendar = new GregorianCalendar(2011, 1, 27);
        PayPeriod period = new PayPeriod();
        period.setUp(calendar.getTime());
        Calendar start = new GregorianCalendar(2011, 1, 16);
        Calendar end = new GregorianCalendar(2011, 1, 28);
        assertEquals(start.getTime(), period.getStartDate());
        assertEquals(end.getTime(), period.getEndDate());
        assertEquals(calendar.getTime(), period.getUseDate());
    }

    @Test
    public void testGetFirstDateShown() throws Exception {
        PayPeriod period = new PayPeriod();
        Calendar test = Calendar.getInstance();
        Calendar expected = Calendar.getInstance();

        // Jan 3 2011 ( Should get Dec 26, 2010
        expected.set(2010, Calendar.DECEMBER, 26, 0, 0, 0);
        test.set(2011, Calendar.JANUARY, 3, 0, 0, 0);
        period.setUp(test.getTime());
        assertEquals(expected.getTime().toString(), period.getFirstDateShown().toString());

        // May 3, 2011,( Should get May 1, 2011
        expected.set(2011, Calendar.MAY, 1, 0, 0, 0);
        test.set(2011, Calendar.MAY, 3, 0, 0, 0);
        period.setUp(test.getTime());
        assertEquals(expected.getTime().toString(), period.getFirstDateShown().toString());

        // Feb 2, 2011, (Should get Jan 30, 2011
        expected.set(2011, Calendar.JANUARY, 30, 0, 0, 0);
        test.set(2011, Calendar.FEBRUARY, 2, 0, 0, 0);
        period.setUp(test.getTime());
        assertEquals(expected.getTime().toString(), period.getFirstDateShown().toString());

        // Feb 17, 2011, (Should get Feb 13, 2011
        expected.set(2011, Calendar.FEBRUARY, 13, 0, 0, 0);
        test.set(2011, Calendar.FEBRUARY, 17, 0, 0, 0);
        period.setUp(test.getTime());
        assertEquals(expected.getTime().toString(), period.getFirstDateShown().toString());
    }
}
