package ro.msg.edu.jbugs.mapper;

import entity.User;
import ro.msg.edu.jbugs.dto.UserDTO;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class UserDTOEntityMapper {

    private UserDTOEntityMapper() {
    }

    public static UserDTO getUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setID(user.getID());
        userDTO.setCounter(user.getCounter());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setMobileNumber(user.getMobileNumber());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setStatus(user.getStatus());

        return userDTO;
    }
    public static User getUser(UserDTO userDTO){
       User user = new User();
       user.setID(userDTO.getID());
       user.setCounter(userDTO.getCounter());
       user.setEmail(userDTO.getEmail());
       user.setFirstName(userDTO.getFirstName());
       user.setLastName(userDTO.getLastName());
       user.setMobileNumber(userDTO.getMobileNumber());
       user.setPassword(userDTO.getPassword());
       user.setUsername(userDTO.getUsername());
       user.setStatus(userDTO.getStatus());

       return user;
        /*
        return  new User(userDTO.getCounter(),
                userDTO.getEmail(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getMobileNumber(),
                userDTO.getPassword(),
                userDTO.getUsername(),
                userDTO.getStatus());
    */
    }
}
