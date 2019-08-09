package ro.msg.edu.jbugs.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Document me.
 *
 * @author msg systems AG; UserModel Name.
 * @since 19.1.2
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = User.FIND_ALL_USERS, query = "select u from UserModel u"),
        @NamedQuery(name = User.CHECK_USERNAME_UNIQUE, query = "select count(u) from UserModel u where u.username = :username"),
        @NamedQuery(name = User.QUERY_SELECT_BY_USERNAME_AND_PASSWORD, query = "select u from UserModel u where u.username = :username and u.password = :password"),
        @NamedQuery(name = User.QUERY_SELECT_BY_USERNAME, query = "select u from UserModel u where u.username = :username")
})
public class User extends BaseEntity{
    public static final Integer USER_STATUS_ACTIVE = 0;
    public static final Integer USER_STATUS_INACTIVE = 1;

    public static final String FIND_ALL_USERS = "findAllUsers";
    public static final String CHECK_USERNAME_UNIQUE = "checkUsernameUnique";
    public static final String QUERY_SELECT_BY_USERNAME_AND_PASSWORD = "user.selectByUsernameAndPassword";
    public static final String QUERY_SELECT_BY_USERNAME = "user.selectByUsername";
    public static final String QUERY_DEACTIVATE_USER = "user.deactivateUser";

    private Integer counter;
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "mobile_number")
    private String mobileNumber;
    private String password;
    private String username;
    private Integer status;

    @OneToMany(mappedBy = "createdByUser")
    private Set<Bug> createdBugs = new HashSet<>();
    @OneToMany(mappedBy = "assignedToUser")
    private Set<Bug> assignedBugs = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Integer counter, String email, String firstName, String lastName, String mobileNumber, String password, String username, Integer status) {
        this.counter = counter;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.username = username;
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public Set<Bug> getCreatedBugs() {
        return createdBugs;
    }

    public Set<Bug> getAssignedBugs() {
        return assignedBugs;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
