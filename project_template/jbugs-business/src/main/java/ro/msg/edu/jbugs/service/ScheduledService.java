package ro.msg.edu.jbugs.service;

import ro.msg.edu.jbugs.dao.NotificationDAO;
import ro.msg.edu.jbugs.manager.impl.BugManager;
import ro.msg.edu.jbugs.manager.impl.CommentManager;
import ro.msg.edu.jbugs.manager.impl.NotificationManager;
import ro.msg.edu.jbugs.manager.remote.BugManagerRemote;
import ro.msg.edu.jbugs.manager.remote.CommentManagerRemote;
import ro.msg.edu.jbugs.manager.remote.NotificationManagerRemote;

import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 * Document me.
 *
 * @author msg systems AG; UserModel Name.
 * @since 19.1.2
 */
@Stateless
public class ScheduledService {
    @EJB
    BugManagerRemote bugManager;
    @EJB
    CommentManagerRemote commentManager;
    @EJB
    NotificationManagerRemote notificationManager;

    //@Schedule(hour = "*", minute = "*", second = "*/30", info = "every 5 seconds")
    public void every30Seconds(){
        Integer del = commentManager.deleteOlderComments();
        Integer upd = bugManager.updateStatusByTargetDate();
        MailService.sendEmail(del, upd);
    }

    //@Schedule(hour = "*/24", minute = "*", second = "*", info = "every day")
    public void everyDay(){
        Integer delNotifications = notificationManager.deleteOldNotifications();
        //mail with integer??
    }

}
