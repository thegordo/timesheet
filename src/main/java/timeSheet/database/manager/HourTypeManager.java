package timeSheet.database.manager;

import timeSheet.database.entity.HourType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: John Lawrence
 * Date: 12/29/10
 * Time: 1:58 PM
 */
public class HourTypeManager {
    private DatabaseManager manager;
    private EntityManager em;

    public HourTypeManager() {
        manager = new DatabaseManager();
        em = manager.getEntityManager();
    }

    public List<HourType> getList() {
        TypedQuery<HourType> query = em.createNamedQuery("findAllTypes", HourType.class);
        return query.getResultList();
    }

    public HourType getType(int id) {
        TypedQuery<HourType> query = em.createNamedQuery("findTypeById", HourType.class);
        query.setParameter("id", id);
        return manager.getSingleResult(query);
    }

    public HourType getType(String name) {
        TypedQuery<HourType> query = em.createNamedQuery("findTypeByName", HourType.class);
        query.setParameter("name", name);
        return manager.getSingleResult(query);
    }

    public HourType saveType(HourType type) {
        HourType existing = getType(type.getName());
        if (existing == null || existing.getId() == type.getId()) {
            return manager.persist(type);
        } else {
            return null;
        }
    }

    public void deleteType(int id) {
        manager.delete(getType(id));
    }

    public String getOptions(boolean limit) {
        List<HourType> types = getList();
        StringBuilder builder = new StringBuilder();
        for (HourType type : types) {
            if (type.getDefaultFlag() && !limit) {
                builder.append("<option ").append("value='").append(type.getId()).append("' selected>").append(type.getName()).append("</option>\n");
            } else {
                builder.append("<option ").append("value='").append(type.getId()).append("'>").append(type.getName()).append("</option>\n");
            }
        }
        return builder.toString();
    }

    public HourType getDefaultType() {
        return manager.getEntityManager().createQuery("Select c from HourType c where c.defaultFlag = true", HourType.class).getSingleResult();
    }
}
