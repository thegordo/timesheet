package timeSheet.dbManager;

import timeSheet.database.entity.Employee;
import timeSheet.database.entity.EmployeeGroup;
import timeSheet.database.manager.DatabaseManager;

import java.util.List;

/**
 * User: John Lawrence
 * Date: 12/26/10
 * Time: 11:32 PM
 */
public class GroupManager {

    public String getGroupSelection(Employee employee) {
        DatabaseManager manager = new DatabaseManager();
        List<EmployeeGroup> groupList = manager.getGroupList();
        StringBuilder groupSelectionList = new StringBuilder();
        for (EmployeeGroup group : groupList) {
            if(employee != null && employee.getGroup().getId() ==  group.getId()) {
                groupSelectionList.append("<option selected=\"selected\" value=").append(group.getId()).append(">").append(group.getName()).append("</option>");
            } else {
                groupSelectionList.append("<option value=").append(group.getId()).append(">").append(group.getName()).append("</option>");
            }
        }
        return groupSelectionList.toString();
    }
}
