package ro.msg.edu.jbugs.dao;

import com.google.common.hash.Hashing;
import ro.msg.edu.jbugs.entity.User;
import ro.msg.edu.jbugs.exception.BusinessException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Document me.
 *
 * @author msg systems AG; UserModel Name.
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
    public User find1(String username, String password) throws BusinessException {

        String hashedPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        User user;
        try {
            user = entityManager.createNamedQuery(User.QUERY_SELECT_BY_USERNAME_AND_PASSWORD, User.class)
                    .setParameter("username", username)
                    .setParameter("password", hashedPassword)
                    .getSingleResult();
            return user;
        } catch(NoResultException e){
            throw new BusinessException("msg-001", "invalid credentials");
        }

    }
    public User find(String username, String password) throws BusinessException {
        User user;
        try {
            user = entityManager.createNamedQuery(User.QUERY_SELECT_BY_USERNAME, User.class)
                    .setParameter("username", username)
                    .getSingleResult();

            if(user.getStatus() == User.USER_STATUS_INACTIVE){
                throw new BusinessException("msg", "user is inactive");
            }

            String hashedPassword = Hashing.sha256()
                    .hashString(password, StandardCharsets.UTF_8)
                    .toString();

            if(!user.getPassword().equals(hashedPassword)){
                if(user.getCounter() >= 4){
                    user.setStatus(User.USER_STATUS_INACTIVE);
                    throw new BusinessException("msg-001", "user was inactivated");
                }
                else{
                    user.setCounter(user.getCounter()+1);
                    throw new BusinessException("msg-001", "incorrect password");
                }
            }
            return user;
        } catch(NoResultException e){
            throw new BusinessException("msg-001", "invalid username");
        }
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
    public boolean isUsernameUnique(String username){
        Long occurences = entityManager.createNamedQuery(User.CHECK_USERNAME_UNIQUE, Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return occurences == 0;
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
