package ro.msg.edu.jbugs.manager.impl;

import dao.BugDAO;
import entity.Bug;
import ro.msg.edu.jbugs.manager.remote.BugManagerRemote;
import ro.msg.edu.jbugs.dto.BugDTO;
import ro.msg.edu.jbugs.mapper.BugDTOEntityMapper;
import ro.msg.edu.jbugs.dto.UserDTO;
import ro.msg.edu.jbugs.mapper.UserDTOEntityMapper;
import ro.msg.edu.jbugs.interceptors.LoggingInterceptor;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.List;
import java.util.stream.Collectors;
@Stateless
@Interceptors(LoggingInterceptor.class)
public class BugManager implements BugManagerRemote {
    @EJB
    private BugDAO bugDAO;

    @Override
    public void insert(BugDTO bugDTO){

        bugDAO.insert(BugDTOEntityMapper.getBug(bugDTO));
    }
    @Override
    public List<BugDTO> findAllCreatedByUser(UserDTO userDTO){
        List<Bug> bugs = bugDAO.findAllCreatedByUser(UserDTOEntityMapper.getUser(userDTO));
        return bugs.stream().map(BugDTOEntityMapper::getBugDTO).collect(Collectors.toList());
    }
    @Override
    public Integer updateStatusByTargetDate(){
        return bugDAO.updateStatusByTargetDate();
    }
}
