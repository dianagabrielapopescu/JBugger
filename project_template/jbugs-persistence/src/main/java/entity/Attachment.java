package entity;

import javax.persistence.*;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Entity
@Table(name = "attachments")
public class Attachment extends BaseEntity{

    @Column(name = "attContent")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bug")
    private Bug bug;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }
}
