package ro.msg.edu.jbugs.service;

import ro.msg.edu.jbugs.manager.impl.BugManager;
import ro.msg.edu.jbugs.manager.impl.CommentManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
public class ScheduledService {
    @EJB
    BugManager bugManager;
    @EJB
    CommentManager commentManager;

    //@Schedule(hour = "*", minute = "*", second = "*/30", info = "every 5 seconds")
    public void every30Seconds(){
        Integer del = commentManager.deleteOlderComments();
        Integer upd = bugManager.updateStatusByTargetDate();
        MailService.sendEmail(del, upd);
    }

}
