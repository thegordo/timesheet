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
    public static final int STRING_LENGTH = 256;

    @Column(length = STRING_LENGTH)
    private String name;

    @Column(length = STRING_LENGTH)
    private String value;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "settingGen")
    protected int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = chopLength(name, STRING_LENGTH);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = chopLength(value, STRING_LENGTH);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
