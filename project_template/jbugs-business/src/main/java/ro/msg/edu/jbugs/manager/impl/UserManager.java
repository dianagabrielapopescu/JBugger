package ro.msg.edu.jbugs.manager.impl;

import dao.UserDAO;
import entity.User;
import ro.msg.edu.jbugs.dto.UserDTO;
import ro.msg.edu.jbugs.manager.remote.UserManagerRemote;
import ro.msg.edu.jbugs.mapper.UserDTOEntityMapper;
import ro.msg.edu.jbugs.interceptors.LoggingInterceptor;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless //paired with EJB... when used
@Interceptors(LoggingInterceptor.class)
public class UserManager implements UserManagerRemote {
    @EJB //paired with Stateless annotation in UserDAO class
    private UserDAO userDAO;

    @Override
    public Map<UserDTO, Integer> getAllUsersCountCreatedBugs(){
        Map<User, Integer> usersCountCreatedBugs = userDAO.getUsersCountCreatedBugs();
        Map<UserDTO, Integer> userDTOsCountCreatedBugs = new HashMap<>();
        usersCountCreatedBugs.forEach((user, count) ->
                userDTOsCountCreatedBugs.put(UserDTOEntityMapper.getUserDTO(user), count));
        return userDTOsCountCreatedBugs;
    }
    @Override
    public void insert(UserDTO userDTO){

        userDAO.insert(UserDTOEntityMapper.getUser(userDTO));
    }
    @Override
    public UserDTO find(Integer id){
        UserDTO userDTO = UserDTOEntityMapper.getUserDTO(userDAO.find(id));
        return userDTO;
    }
    @Override
    public List<UserDTO> findAll(){
        List<User> users = userDAO.findAll();
        return users.stream().map(UserDTOEntityMapper::getUserDTO).collect(Collectors.toList());
    }
}
