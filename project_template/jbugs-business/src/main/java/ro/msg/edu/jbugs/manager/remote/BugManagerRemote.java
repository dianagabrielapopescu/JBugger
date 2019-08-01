package ro.msg.edu.jbugs.manager.remote;

import entity.Bug;
import ro.msg.edu.jbugs.dto.BugDTO;
import ro.msg.edu.jbugs.dto.UserDTO;
import ro.msg.edu.jbugs.mapper.BugDTOEntityMapper;
import ro.msg.edu.jbugs.mapper.UserDTOEntityMapper;

import javax.ejb.Remote;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Remote
public interface BugManagerRemote {
    void insert(BugDTO bugDTO);
    List<BugDTO> findAllCreatedByUser(UserDTO userDTO);
    Integer updateStatusByTargetDate();
}
