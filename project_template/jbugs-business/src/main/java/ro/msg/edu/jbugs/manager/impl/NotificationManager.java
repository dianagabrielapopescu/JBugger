package ro.msg.edu.jbugs.manager.impl;

import ro.msg.edu.jbugs.dao.NotificationDAO;
import ro.msg.edu.jbugs.manager.remote.NotificationManagerRemote;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Document me.
 *
 * @author msg systems AG; UserModel Name.
 * @since 19.1.2
 */
@Stateless
public class NotificationManager implements NotificationManagerRemote {

    @EJB
    NotificationDAO notificationDAO;

    @Override
    public Integer deleteOldNotifications() {
        return notificationDAO.deleteOldNotifications();
    }
}
