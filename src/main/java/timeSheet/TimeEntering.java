package timeSheet;

import timeSheet.database.entity.Employee;
import timeSheet.database.entity.Hours;
import timeSheet.database.manager.HoursManager;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: John Lawrence
 * Date: 2/27/11
 * Time: 10:56 PM
 */
public class TimeEntering {
    private Employee sessionEmployee;
    private Employee employee;
    private PayPeriod period;

    public TimeEntering(Employee sessionEmployee, Employee employee, PayPeriod period) {
        this.sessionEmployee = sessionEmployee;
        this.employee = employee;
        this.period = period;
    }


    public String getTimeTable(String location) {
        StringBuilder tableString = new StringBuilder();
        tableString.append("<table>\n<tr >\n<td > Sunday </td >\n<td > Monday </td >\n<td > Tuesday </td >\n<td > Wednesday </td >\n" );
        tableString.append("<td > Thursday </td >\n<td > Friday </td >\n<td > Saturday </td >\n<td class=\"total\" > Total </td >\n</tr > ");
        tableString.append(getWeek(0, location));
        tableString.append(getWeek(1, location));
        tableString.append(getWeek(2, location));
        tableString.append("</table>");
        return tableString.toString();
    }

    private String getWeek(int weekNum, String location) {
        HoursManager hoursManager = new HoursManager();
        StringBuilder tableRow = new StringBuilder();
        tableRow.append("<tr>\n");
        Date date = period.getFirstDateShown();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int weeklyHoursTotal = 0;
        for (int index = 0; index < 8; index++) {
            int factor = index + weekNum * 7;
            if (index == 7) {
                tableRow.append("<td class='total' id='week").append(weekNum).append("total'>Total:<br />").append(weeklyHoursTotal).append("</td>");
            } else {
                if (index == 0) {
                    cal.add(Calendar.DAY_OF_MONTH, factor);
                } else {
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                }
                Date tempDate = cal.getTime();
                tableRow.append("<td class='hourTable' >").append("<p class='dateNumber' > ").append(cal.get(Calendar.DAY_OF_MONTH)).append(" </p > ");
                List<Hours> hourList = hoursManager.getHoursByEmployeeAndDate(employee, tempDate);
                if (hourList.size() > 0) {
                    for (Hours hours : hourList) {
                        if (hours.getType().getPaid()) {
                            weeklyHoursTotal += hours.getHours();
                        }
                        tableRow.append("Hours: ").append(hours.getHours()).append(" <br />(").append(hours.getType().getName()).append(")<br />");
                        Calendar temp = Calendar.getInstance();
                        temp.setTime(tempDate);
                        int tempDay = temp.get(Calendar.DAY_OF_MONTH);
                        if (tempDay >= period.getStart().get(Calendar.DAY_OF_MONTH) && tempDay <= period.getEnd().get(Calendar.DAY_OF_MONTH) || sessionEmployee.getRole().isTimeManager()) {
                            tableRow.append("<a href='library/deleteHours.jsp?id=").append(hours.getId()).append("&location=").append(location).append("'>Delete</a><br />");
                        }
                        if (!hours.getEmployeeApproval() && sessionEmployee.getId() == employee.getId()) {
                            tableRow.append("<a href='library/saveApproval.jsp?id=").append(hours.getId()).append("&manager=false&location=").append(location).append("'>approve</a><br />");
                        }
                        if (!hours.getManagerApproval() && sessionEmployee.getId() != employee.getId() && sessionEmployee.getRole().isTimeManager()) {
                            tableRow.append("<a href='library/saveApproval.jsp?id=").append(hours.getId()).append("&manager=true&location=").append(location).append("'>approve</a><br />");
                        }
                    }
                } else {
                    tableRow.append(" ");
                }
                tableRow.append("</td>");
            }
        }
        tableRow.append("</tr>\n");
        return tableRow.toString();
    }

    public String getPayPeriodTotals() {
        HoursManager hoursManager = new HoursManager();
        Double totalHoursWorkedInPayPeriod = hoursManager.getTotalHoursWorkedInPayPeriod(employee, period);
        if (totalHoursWorkedInPayPeriod == null) {
            totalHoursWorkedInPayPeriod = 0.0;
        }
        return "<p>Totals hours for the pay period: " + totalHoursWorkedInPayPeriod + "</p>";
    }
}
