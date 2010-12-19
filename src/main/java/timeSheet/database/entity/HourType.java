package timeSheet.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * User: John Lawrence
 * Date: 12/13/10
 * Time: 1:34 AM
 */
@Entity
public class HourType extends BaseObject {
    @Column(length = 256)
    public String name;

    @Column
    public Boolean paid;

    @Column
    public Boolean defaultFlag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }
}
