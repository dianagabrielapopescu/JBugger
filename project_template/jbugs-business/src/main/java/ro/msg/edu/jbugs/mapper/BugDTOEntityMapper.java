package ro.msg.edu.jbugs.mapper;

import entity.Bug;
import ro.msg.edu.jbugs.dto.BugDTO;

public class BugDTOEntityMapper {
    private BugDTOEntityMapper() {
    }

    public static Bug getBug(BugDTO bugDTO){
        Bug bug = new Bug();
        bug.setID(bugDTO.getID());
        bug.setAssignedToUser(UserDTOEntityMapper.getUser(bugDTO.getAssignedToUser()));
        bug.setCreatedByUser(UserDTOEntityMapper.getUser(bugDTO.getCreatedByUser()));
        bug.setDescription(bugDTO.getDescription());
        bug.setFixedVersion(bugDTO.getFixedVersion());
        bug.setSeverity(bugDTO.getSeverity());
        bug.setStatus(bugDTO.getStatus());
        bug.setTargetDate(bugDTO.getTargetDate());
        bug.setTitle(bugDTO.getTitle());
        bug.setVersion(bugDTO.getVersion());
        return bug;
    }

    public static BugDTO getBugDTO(Bug bug){
        BugDTO bugDTO = new BugDTO();
        bugDTO.setID(bug.getID());
        bugDTO.setAssignedToUser(UserDTOEntityMapper.getUserDTO(bug.getAssignedToUser()));
        bugDTO.setCreatedByUser(UserDTOEntityMapper.getUserDTO(bug.getCreatedByUser()));
        bugDTO.setDescription(bug.getDescription());
        bugDTO.setFixedVersion(bug.getFixedVersion());
        bugDTO.setSeverity(bug.getSeverity());
        bugDTO.setStatus(bug.getStatus());
        bugDTO.setTargetDate(bug.getTargetDate());
        bugDTO.setTitle(bug.getTitle());
        bugDTO.setVersion(bug.getVersion());
        return bugDTO;
    }
}
