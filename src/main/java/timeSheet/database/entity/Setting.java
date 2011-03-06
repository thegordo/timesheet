package timeSheet.database.entity;

import javax.persistence.*;

/**
 * User: John Lawrence
 * Date: 12/10/10
 * Time: 1:04 AM
 */
@Entity
@SequenceGenerator(name = "settingGen", allocationSize = 1)
public class Setting extends BaseObject {
    @Column(length = 256)
    private String name;

    @Column(length = 256)
    private String value;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "settingGen")
    protected int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
