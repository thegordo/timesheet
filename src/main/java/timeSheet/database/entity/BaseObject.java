package timeSheet.database.entity;


import javax.persistence.MappedSuperclass;

/**
 * User: John Lawrence
 * Date: 12/13/10
 * Time: 12:47 AM
 */
@MappedSuperclass
public abstract class BaseObject {
    public abstract int getId();
    public abstract void setId(int id);
}
