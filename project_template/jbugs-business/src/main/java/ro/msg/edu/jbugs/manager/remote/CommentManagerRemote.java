package ro.msg.edu.jbugs.manager.remote;

import javax.ejb.Remote;

/**
 * Document me.
 *
 * @author msg systems AG; UserModel Name.
 * @since 19.1.2
 */
@Remote
public interface CommentManagerRemote {
    Integer deleteOlderComments();
}
