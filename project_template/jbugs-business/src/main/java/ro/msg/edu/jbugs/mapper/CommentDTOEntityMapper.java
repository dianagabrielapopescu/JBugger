package ro.msg.edu.jbugs.mapper;

import entity.Comment;
import ro.msg.edu.jbugs.dto.CommentDTO;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class CommentDTOEntityMapper {
    private CommentDTOEntityMapper() {
    }

    public static Comment getComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setID(commentDTO.getID());
        comment.setText(commentDTO.getText());
        comment.setDate(commentDTO.getDate());
        comment.setUser(UserDTOEntityMapper.getUser(commentDTO.getUserDTO()));
        comment.setBug(BugDTOEntityMapper.getBug(commentDTO.getBugDTO()));
        return comment;
    }

    public static CommentDTO getCommentDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setID(comment.getID());
        commentDTO.setText(comment.getText());
        commentDTO.setDate(comment.getDate());
        commentDTO.setUserDTO(UserDTOEntityMapper.getUserDTO(comment.getUser()));
        commentDTO.setBugDTO(BugDTOEntityMapper.getBugDTO(comment.getBug()));
        return commentDTO;
    }
}
