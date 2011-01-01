package timeSheet.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * User: John Lawrence
 * Date: 12/13/10
 * Time: 1:34 AM
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findAllTypes", query = "SELECT ht FROM HourType ht"),
        @NamedQuery(name = "findTypeByName", query = "SELECT C from HourType C where UPPER(C.name) = UPPER(:name)"),
        @NamedQuery(name = "findTypeById", query = "SELECT C from HourType C where C.id = :id")
})
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

    public enum Field {
        id,
        name,
        paid,
        defaultFlag,
    }
}
