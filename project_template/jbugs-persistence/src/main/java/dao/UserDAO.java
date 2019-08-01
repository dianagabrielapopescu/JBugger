package dao;

import entity.User;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
public class UserDAO {
    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager entityManager;

    public User find(Integer id){

        User user = entityManager.find(User.class, id);
        return user;
    }
    public List<User> findAll(){
        Query query = entityManager.createNamedQuery(User.FIND_ALL_USERS, User.class);
        List<User> users = query.getResultList();
        return users;
    }
    public Map<User, Integer> getUsersCountCreatedBugs(){
        Query query = entityManager.createNativeQuery(
                "select u.id, count(b.CREATED_ID) " +
                        "from users u inner join bugs b on u.id = b.CREATED_ID " +
                        "group by b.CREATED_ID");
        List<Object[]> resultList = query.getResultList();

        Map<User, Integer> usersCountCreatedBugsMap = new HashMap<>();
        resultList.forEach(elem -> {
            User user = this.find(((Long)elem[0]).intValue());
            usersCountCreatedBugsMap.put(user, ((Long)elem[1]).intValue());});
        return usersCountCreatedBugsMap;
    }
    public void insert(User user){

        entityManager.persist(user);
        //entityManager.flush();//no need to...
    }
    public Integer insertGetID(User user){
        entityManager.persist(user);
        entityManager.flush();
        return user.getID();
    }
}
