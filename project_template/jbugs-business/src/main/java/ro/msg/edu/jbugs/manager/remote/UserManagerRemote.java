package ro.msg.edu.jbugs.manager.remote;

import entity.User;
import ro.msg.edu.jbugs.dto.UserDTO;
import ro.msg.edu.jbugs.mapper.UserDTOEntityMapper;

import javax.ejb.Remote;
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
@Remote
public interface UserManagerRemote {
    Map<UserDTO, Integer> getAllUsersCountCreatedBugs();
    void insert(UserDTO userDTO);
    UserDTO find(Integer id);
    List<UserDTO> findAll();
}
