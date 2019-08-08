package ro.msg.edu.jbugs.dto;

import java.io.Serializable;
import java.sql.Date;

public class BugDTO implements Serializable {
    private Integer ID;
    private String title;
    private String description;
    private String version;
    private Date targetDate;
    private String status;
    private String fixedVersion;
    private String severity;
    private UserDTO createdByUser;
    private UserDTO assignedToUser;

    @Override
    public String toString() {
        return "BugDTO{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", version='" + version + '\'' +
                ", targetDate=" + targetDate +
                ", status='" + status + '\'' +
                ", fixedVersion='" + fixedVersion + '\'' +
                ", severity='" + severity + '\'' +
                ", createdByUser=" + createdByUser +
                ", assignedToUser=" + assignedToUser +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFixedVersion() {
        return fixedVersion;
    }

    public void setFixedVersion(String fixedVersion) {
        this.fixedVersion = fixedVersion;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public UserDTO getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(UserDTO createdByUser) {
        this.createdByUser = createdByUser;
    }

    public UserDTO getAssignedToUser() {
        return assignedToUser;
    }

    public void setAssignedToUser(UserDTO assignedToUser) {
        this.assignedToUser = assignedToUser;
    }
}
