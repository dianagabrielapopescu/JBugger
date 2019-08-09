package ro.msg.edu.jbugs.manager.remote;

import ro.msg.edu.jbugs.dto.UserDTO;
import ro.msg.edu.jbugs.exception.BusinessException;

import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

/**
 * Document me.
 *
 * @author msg systems AG; UserModel Name.
 * @since 19.1.2
 */
@Remote
public interface UserManagerRemote {
    Map<UserDTO, Integer> getAllUsersCountCreatedBugs();
    UserDTO insert(UserDTO userDTO);
    UserDTO find(Integer id);
    List<UserDTO> findAll();
    UserDTO login(String username, String password) throws BusinessException;
}
