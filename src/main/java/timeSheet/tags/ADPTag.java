package timeSheet.tags;

import timeSheet.PayPeriod;
import timeSheet.database.entity.Employee;
import timeSheet.database.manager.EmployeeManager;
import timeSheet.database.manager.HoursManager;
import timeSheet.html.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * User: John Lawrence
 * Date: 3/24/11
 * Time: 11:28 PM
 */
public class ADPTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        EmployeeManager employeeManager = new EmployeeManager();
        List<Employee> employeeList = employeeManager.getEmployeeList();
        Table table = new Table();
        table.setHtmlClass("report");
        TableHeader header = new TableHeader();
        TableRow headerRow = new TableRow();
        header.setRow(headerRow);
        headerRow.addHeader(UtilHtml.getSimpleTableData("File Number"));
        headerRow.addHeader(UtilHtml.getSimpleTableData("Employee Name"));
        headerRow.addHeader(UtilHtml.getSimpleTableData("Regular Hours"));
        headerRow.addHeader(UtilHtml.getSimpleTableData("Commission"));
        headerRow.addHeader(UtilHtml.getSimpleTableData("Bonus"));
        headerRow.addHeader(UtilHtml.getSimpleTableData("Reg Earnings"));
        headerRow.addHeader(UtilHtml.getSimpleTableData("Adjust"));
        headerRow.addHeader(UtilHtml.getSimpleTableData("NC Earnings"));
        headerRow.addHeader(UtilHtml.getSimpleTableData("NC Deduction"));
        table.setHeader(header);
        for (Employee employee : employeeList) {
            int empID = employee.getId();
            TableRow row = new TableRow();
            row.addData(UtilHtml.getSimpleTableData(employee.getFileNumber()));
            row.addData(UtilHtml.getSimpleTableData(employee.getName()));
            TableData data = UtilHtml.getSimpleTableInputData(empID + "Hours", HTMLInputType.text);
            if (! employee.getSalary()) {
                HoursManager hoursManager = new HoursManager();
                Double hoursWorked = hoursManager.getTotalHoursWorkedInPayPeriod(employee, new PayPeriod());
                if (hoursWorked == null) {
                    hoursWorked = 0.0;
                }
                data = UtilHtml.getSimpleTableInputData(empID + "Hours", HTMLInputType.text, "" + hoursWorked);
            }
            row.addData(data);
            row.addData(UtilHtml.getSimpleTableInputData(empID + "Commission", HTMLInputType.text));
            row.addData(UtilHtml.getSimpleTableInputData(empID + "bonus", HTMLInputType.text));
            row.addData(UtilHtml.getSimpleTableInputData(empID + "RegEarnings", HTMLInputType.text));
            row.addData(UtilHtml.getSimpleTableInputData(empID + "adjust", HTMLInputType.text));
            row.addData(UtilHtml.getSimpleTableInputData(empID + "NCRegEarnings", HTMLInputType.text));
            row.addData(UtilHtml.getSimpleTableInputData(empID + "NCDeduction", HTMLInputType.text));
            table.addRow(row);
        }
        printTable(table);
        return super.doStartTag();
    }

    private void printTable(Table table) {
        try {
            JAXBContext context = JAXBContext.newInstance(Table.class, Input.class, Label.class);
            context.createMarshaller().marshal(table, pageContext.getOut());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
