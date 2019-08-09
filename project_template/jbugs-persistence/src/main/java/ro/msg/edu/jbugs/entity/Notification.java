package ro.msg.edu.jbugs.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Document me.
 *
 * @author msg systems AG; UserModel Name.
 * @since 19.1.2
 */
@Entity
@Table(name = "notifications")
@NamedQueries({
        @NamedQuery(name = Notification.DELETE_OLD_NOTIFICATIONS, query = "delete from Notification n where n.date < :var_date")
})
public class Notification extends BaseEntity{

    public static final String DELETE_OLD_NOTIFICATIONS = "deleteNotificationsOlderThan30days";

    private Date date;
    private String message;
    private String type;
    private String url;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
