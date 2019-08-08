package ro.msg.edu.jbugs.dao;

import ro.msg.edu.jbugs.entity.Bug;
import ro.msg.edu.jbugs.entity.Comment;
import ro.msg.edu.jbugs.entity.User;
import org.apache.commons.lang.time.DateUtils;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
public class CommentDAO {
    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager entityManager;

    public Comment find(Integer id){
        Comment comment = entityManager.find(Comment.class, id);
        return comment;
    }
    public Integer deleteOlderComments(){
        Integer beforeCount = ((Number)entityManager
                        .createNamedQuery(Comment.GET_COUNT_COMMENTS, Integer.class)
                        .getSingleResult()).intValue();

        int i = entityManager.createNamedQuery(Comment.DELETE_OLDER_THAN_1_YEAR_COMMENTS)
                .setParameter("var_date", DateUtils.addYears(new Date(Calendar.getInstance().getTime().getTime()), -1))
                .executeUpdate();

        Integer afterCount = ((Number)entityManager
                .createNamedQuery(Comment.GET_COUNT_COMMENTS, Integer.class)
                .getSingleResult()).intValue();
        //return beforeCount-afterCount;
        return i;
    }
    public List<Comment> findAllOfUser(User user){
        Query query = entityManager.createNamedQuery(Comment.FIND_COMMENTS_USER_ID, Comment.class);
        List<Comment> comments = query.setParameter("id", user.getID()).getResultList();
        return comments;
    }
    public List<Comment> findAllOfBug(Bug bug){
        Query query = entityManager.createNamedQuery(Comment.FIND_COMMENTS_BUG_ID, Comment.class);
        List<Comment> comments = query.setParameter("id", bug.getID()).getResultList();
        return comments;
    }
    public void insert(Comment comment){
        entityManager.persist(comment);
    }
    public Integer insertGetID(Comment comment){
        entityManager.persist(comment);
        entityManager.flush();
        return comment.getID();
    }
}
