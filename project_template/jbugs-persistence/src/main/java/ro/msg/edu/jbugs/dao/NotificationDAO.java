package ro.msg.edu.jbugs.dao;

import ro.msg.edu.jbugs.entity.Notification;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
public class NotificationDAO {
    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager entityManager;

    public Integer deleteOldNotifications(){
        return entityManager.createNamedQuery(Notification.DELETE_OLD_NOTIFICATIONS, Integer.class)
                .setParameter("var_date", new Date(Calendar.getInstance().getTime().getTime()))
                .executeUpdate();
    }
}
