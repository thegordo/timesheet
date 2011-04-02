package timeSheet.tags;

import timeSheet.database.entity.Employee;
import timeSheet.database.manager.EmployeeManager;
import timeSheet.database.manager.SettingsManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * User: John Lawrence
 * Date: 3/31/11
 * Time: 10:28 PM
 */
public class ADPReportTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        Map<String, String[]> parameters = pageContext.getRequest().getParameterMap();
        String companyCode = new SettingsManager().getCompanyCode();
        try {
            out.println("\"!Do not change rows and column headers preceded by \"\"!\"\".  Only employee data can be modified.\",,,,,,,,,,,\n" +
                    "!,,,,Name,Reg Hours,Commission,Bonus,Reg Earnings,Adjust,NC Earnings,NC Deduction\n" +
                    "!CO CODE,BATCH ID,FILE #,BATCH DESCRIPTION,,,,,,Misc,,\n");

            EmployeeManager employeeManager = new EmployeeManager();
            List<Employee> employeeList = employeeManager.getEmployeeList();
            for (Employee employee : employeeList) {
                StringBuilder line = new StringBuilder();
                String key = employee.getId() + "Hours";
                if (parameters.get(key) != null) {
                    line.append(companyCode).append(",");
                    line.append(parameters.get("batchId")[0]).append(",");
                    line.append(employee.getFileNumber()).append(",");
                    line.append(parameters.get("batchDescription")[0]).append(",");
                    line.append(employee.getName()).append(",");
                    line.append(parameters.get(key)[0]).append(",");
                    key = employee.getId() + "Commission";
                    line.append(parameters.get(key)[0]).append(",");
                    key = employee.getId() + "bonus";
                    line.append(parameters.get(key)[0]).append(",");
                    key = employee.getId() + "RegEarnings";
                    line.append(parameters.get(key)[0]).append(",");
                    key = employee.getId() + "adjust";
                    line.append(parameters.get(key)[0]).append(",");
                    key = employee.getId() + "NCRegEarnings";
                    line.append(parameters.get(key)[0]).append(",");
                    key = employee.getId() + "NCDeduction";
                    line.append(parameters.get(key)[0]);

                    out.println(line.toString());
                }
            }
            out.println("!,,,,,,,,,,,\n" +
                    "!Column1,,CO CODE,CO CODE,,,,,,,,\n" +
                    "!Column2,,BATCH ID,BATCH ID,,,,,,,,\n" +
                    "!Column3,,FILE #,FILE #,,,,,,,,\n" +
                    "!Column4,,BATCH DESCRIPTION,BATCH DESCRIPTION,,,,,,,,\n" +
                    "!Column5,Name,,Employee Name,,,,,,,,\n" +
                    "!Column6,Reg Hours,,Reg Hours,,,,,,,,\n" +
                    "!Column7,Commission,,Earnings 3 Code,Earnings 3 Amount,C,,,,,,\n" +
                    "!Column8,Bonus,,Earnings 3 Code,Earnings 3 Amount,B,,,,,,\n" +
                    "!Column9,Reg Earnings,,Reg Earnings,,,,,,,,\n" +
                    "!Column10,Adjust,Misc,Adjust Ded Code,Adjust Ded Amount,M,,,,,,\n" +
                    "!Column11,NC Earnings,,Earnings 3 Code,Earnings 3 Amount,NC,,,,,,\n" +
                    "!Column12,NC Deduction,,Adjust Ded Code,Adjust Ded Amount,NC,,,,,,");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return super.doStartTag();
    }
}
