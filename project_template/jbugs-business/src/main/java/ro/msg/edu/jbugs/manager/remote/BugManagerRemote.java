package ro.msg.edu.jbugs.manager.remote;

import ro.msg.edu.jbugs.dto.BugDTO;
import ro.msg.edu.jbugs.dto.UserDTO;

import javax.ejb.Remote;
import java.util.List;

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
