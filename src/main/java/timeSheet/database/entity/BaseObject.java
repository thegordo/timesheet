package timeSheet.database.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * User: John Lawrence
 * Date: 12/13/10
 * Time: 12:47 AM
 */
@MappedSuperclass
public class BaseObject {
    @Id
    @GeneratedValue
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
