package ro.msg.edu.jbugs.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bugs")
@NamedQueries({
        @NamedQuery(name = Bug.FIND_BUGS_CREATED_ID, query = "select b from Bug b where b.createdByUser.ID = :id"),
        @NamedQuery(name = Bug.UPDATE_STATUS_BY_TARGET_DATE, query = "update Bug b SET b.status = 'closed' where b.targetDate < :var_date and b.status <> 'closed'")
})
public class Bug extends BaseEntity {
    public static final String FIND_BUGS_CREATED_ID = "findBugsCreatedByUser";
    public static final String UPDATE_STATUS_BY_TARGET_DATE = "updateStatusByTargetDate";

    private String title;
    private String description;
    private String version;
    private Date targetDate;
    private String status;
    private String fixedVersion;
    private String severity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CREATED_ID")
    private User createdByUser;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ASSIGNED_ID")
    private User assignedToUser;

    @OneToMany(mappedBy = "bug")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "bug")
    private Set<Attachment> attachments = new HashSet<>();

    public Bug() {
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
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

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public User getAssignedToUser() {
        return assignedToUser;
    }

    public void setAssignedToUser(User assignedToUser) {
        this.assignedToUser = assignedToUser;
    }
}
