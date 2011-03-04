package timeSheet;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * User: John Lawrence
 * Date: 2/27/11
 * Time: 9:50 AM
 */
public class PayPeriod {
    private Date startDate;
    private Date endDate;
    private Date useDate;

    public PayPeriod() {
        setUp(new Date());
    }

    public PayPeriod(Date date) {
        setUp(date);
    }

    public void setUp(Date date) {
        useDate = date;
        Calendar cal =  Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        if (day < 15) {
            startDate = new GregorianCalendar(year, month, 1).getTime();
            endDate = new GregorianCalendar(year, month, 15).getTime();
        } else {
            startDate = new GregorianCalendar(year, month, 16).getTime();
            endDate = new GregorianCalendar(year, month, cal.getActualMaximum(Calendar.DAY_OF_MONTH)).getTime();
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getUseDate() {
        return useDate;
    }


    public String toString() {
        DateFormat format = UtilWeb.getDateFormat();
        return format.format(startDate) + " - " + format.format(endDate);
    }

    public Date getFirstDateShown() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cal.getTime());
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        day = day - dayOfWeek;
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public String getPayPeriodSelections() {
        StringBuilder options = new StringBuilder();
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        int number = start.get(Calendar.DAY_OF_MONTH);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int endSelection = end.get(Calendar.DAY_OF_MONTH);
        Calendar today = Calendar.getInstance();
        today.setTime(useDate);
        int now = today.get(Calendar.DAY_OF_MONTH);
        while (number <= endSelection) {
            if (number == now) {
                options.append("<option selected>").append(number).append("</option>\n");
            } else {
                options.append("<option>").append(number).append("</option>\n");
            }
            number ++;
        }
        return options.toString();
    }

    public Calendar getStart() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        return cal;
    }

    public Calendar getEnd() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        return cal;
    }
}
