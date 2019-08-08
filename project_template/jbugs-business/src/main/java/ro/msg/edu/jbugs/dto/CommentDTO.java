package ro.msg.edu.jbugs.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class CommentDTO implements Serializable {

    private Integer ID;
    private String text;
    private Date date;
    private UserDTO userDTO;
    private BugDTO bugDTO;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public BugDTO getBugDTO() {
        return bugDTO;
    }

    public void setBugDTO(BugDTO bugDTO) {
        this.bugDTO = bugDTO;
    }
}
