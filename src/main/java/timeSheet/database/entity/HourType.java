package timeSheet.database.entity;

import javax.persistence.*;

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
@SequenceGenerator(name = "hourTypeGen", allocationSize = 1)
public class HourType extends BaseObject {
    @Column(length = 256)
    private String name;

    @Column
    private Boolean paid;

    @Column
    private Boolean defaultFlag;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hourTypeGen")
    protected int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum Field {
        id,
        name,
        paid,
        defaultFlag,
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HourType hourType = (HourType) o;
        return name.equals(hourType.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
