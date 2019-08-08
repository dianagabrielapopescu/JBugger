package ro.msg.edu.jbugs.manager.impl;

import com.google.common.hash.Hashing;
import ro.msg.edu.jbugs.dao.UserDAO;
import ro.msg.edu.jbugs.entity.User;
import ro.msg.edu.jbugs.dto.UserDTO;
import ro.msg.edu.jbugs.exception.BusinessException;
import ro.msg.edu.jbugs.manager.remote.UserManagerRemote;
import ro.msg.edu.jbugs.mapper.UserDTOEntityMapper;
import ro.msg.edu.jbugs.interceptors.LoggingInterceptor;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.nio.charset.StandardCharsets;
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
    public UserDTO login(String username, String password) throws BusinessException {
        return UserDTOEntityMapper.getUserDTO(userDAO.find(username, password));
    }

    @Override
    public Map<UserDTO, Integer> getAllUsersCountCreatedBugs(){
        Map<User, Integer> usersCountCreatedBugs = userDAO.getUsersCountCreatedBugs();
        Map<UserDTO, Integer> userDTOsCountCreatedBugs = new HashMap<>();
        usersCountCreatedBugs.forEach((user, count) ->
                userDTOsCountCreatedBugs.put(UserDTOEntityMapper.getUserDTO(user), count));
        return userDTOsCountCreatedBugs;
    }
    @Override
    public UserDTO insert(UserDTO userDTO){
        userDTO.setUsername(this.generateUsername(userDTO.getFirstName(), userDTO.getLastName()));

        String hashedPassword = Hashing.sha256()
                .hashString(userDTO.getPassword(), StandardCharsets.UTF_8)
                .toString();
        userDTO.setPassword(hashedPassword);

        userDTO.setID(userDAO.insertGetID(UserDTOEntityMapper.getUser(userDTO)));

        return userDTO;
    }
    public String generateUsername(String firstName, String lastName) {
        String aux;
        if(lastName.length() >= 5) {
            aux = lastName.substring(0, 5);
        }
        else{
            aux = lastName;
        }
        String intermUsername = (aux + firstName.charAt(0)).toLowerCase();

        for (int charPosition = 1; !userDAO.isUsernameUnique(intermUsername); charPosition++){
            if(charPosition < firstName.length()){
                intermUsername = intermUsername + firstName.charAt(charPosition);
            }
            else{
                intermUsername = intermUsername + "x";
            }
            intermUsername = intermUsername.toLowerCase();
        }
        return intermUsername;
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
