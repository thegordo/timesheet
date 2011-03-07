package timeSheet.database.manager;

import timeSheet.PayPeriod;
import timeSheet.database.entity.Employee;
import timeSheet.database.entity.Hours;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * User: John Lawrence
 * Date: 2/28/11
 * Time: 10:01 PM
 */
public class HoursManager {
    private DatabaseManager manager;
    private EntityManager em;

    public HoursManager() {
        manager = new DatabaseManager();
        em = manager.getEntityManager();
    }

    public List<Hours> getHoursByEmployeeAndDate(Employee employee, Date date) {
        String queryString = "Select c from Hours c where c.employee = :employee and c.date = :date";
        TypedQuery<Hours> query = em.createQuery(queryString, Hours.class);
        query.setParameter("employee", employee);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public Double getTotalHoursWorkedInPayPeriod(Employee employee, PayPeriod period) {
        Query query = em.createNamedQuery("findSumOfPaidHours");
        query.setParameter("employee", employee);
        query.setParameter("start", period.getStartDate());
        query.setParameter("end", period.getEndDate());
        return (Double) query.getSingleResult();
    }

    public Hours saveHours(Hours hours) {
        em.getTransaction().begin();
        hours = em.merge(hours);
        em.persist(hours);
        em.getTransaction().commit();
        return hours;
    }

    public void removeByID(int id) {
        em.getTransaction().begin();
        Hours hours = getHoursByID(id);
        em.remove(hours);
        em.getTransaction().commit();
    }

    public Hours getHoursByID(int id) {
        TypedQuery<Hours> query = em.createQuery("SELECT c from Hours c where c.id =" + id, Hours.class);
        return query.getSingleResult();
    }
}
