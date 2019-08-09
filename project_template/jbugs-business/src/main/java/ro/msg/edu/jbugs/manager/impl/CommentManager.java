package ro.msg.edu.jbugs.manager.impl;

import ro.msg.edu.jbugs.dao.CommentDAO;
import ro.msg.edu.jbugs.interceptors.LoggingInterceptor;
import ro.msg.edu.jbugs.manager.remote.CommentManagerRemote;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * Document me.
 *
 * @author msg systems AG; UserModel Name.
 * @since 19.1.2
 */
@Stateless
@Interceptors(LoggingInterceptor.class)
public class CommentManager implements CommentManagerRemote {
    @EJB
    CommentDAO commentDAO;

    @Override
    public Integer deleteOlderComments(){

        return commentDAO.deleteOlderComments();
    }
}
