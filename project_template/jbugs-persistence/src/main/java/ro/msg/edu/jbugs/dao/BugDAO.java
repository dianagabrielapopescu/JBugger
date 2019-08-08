package ro.msg.edu.jbugs.dao;

import ro.msg.edu.jbugs.entity.Bug;
import ro.msg.edu.jbugs.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
@Stateless
public class BugDAO {
    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager entityManager;

    public Bug find(Integer id){
        Bug bug = entityManager.find(Bug.class, id);
        return bug;
    }
    public List<Bug> findAllCreatedByUser(User user){
        Query query = entityManager.createNamedQuery(Bug.FIND_BUGS_CREATED_ID, Bug.class);
        List<Bug> bugs = query.setParameter("id", user.getID()).getResultList();
        return bugs;
    }
    public Integer updateStatusByTargetDate(){
        return entityManager
                .createNamedQuery(Bug.UPDATE_STATUS_BY_TARGET_DATE, Integer.class)
                .setParameter("var_date", new Date(Calendar.getInstance().getTime().getTime()))
                .executeUpdate();
    }
    public Integer getCountCreatedBugs(User user){
        return this.findAllCreatedByUser(user).size();
    }
    public void insert(Bug bug){
        entityManager.persist(bug);
    }
    public Integer insertGetID(Bug bug){
        entityManager.persist(bug);
        entityManager.flush();
        return bug.getID();
    }
}
