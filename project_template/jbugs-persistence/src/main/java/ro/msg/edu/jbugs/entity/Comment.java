package ro.msg.edu.jbugs.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Entity
@Table(name = "comments")
@NamedQueries({
        @NamedQuery(name = Comment.GET_COUNT_COMMENTS, query = "select COUNT(c.ID) from Comment c"),
        @NamedQuery(name = Comment.FIND_COMMENTS_USER_ID, query = "select c from Comment c where c.user.ID = :id"),
        @NamedQuery(name = Comment.FIND_COMMENTS_BUG_ID, query = "select c from Comment c where c.bug.ID = :id"),
        @NamedQuery(name = Comment.DELETE_OLDER_THAN_1_YEAR_COMMENTS, query = "DELETE FROM Comment c WHERE c.date < :var_date")
})
public class Comment extends BaseEntity{
    public static final String GET_COUNT_COMMENTS = "findAllComments";
    public static final String FIND_COMMENTS_USER_ID = "findCommentsOfUser";
    public static final String FIND_COMMENTS_BUG_ID = "findCommentsOfBug";
    public static final String DELETE_OLDER_THAN_1_YEAR_COMMENTS = "deleteOldComments";

    private String text;
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bug_id")
    private Bug bug;

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "text='" + text + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", bug=" + bug +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }
}
