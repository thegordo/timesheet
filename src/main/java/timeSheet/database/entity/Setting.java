package timeSheet.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * User: John Lawrence
 * Date: 12/10/10
 * Time: 1:04 AM
 */
@Entity
public class Setting extends BaseObject {
    @Column(length = 256)
    private String name;

    @Column(length = 256)
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
