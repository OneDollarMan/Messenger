package rgr.Messenger.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

@Entity
@JsonIgnoreProperties({ "password", "email", "isActivated", "activationCode", "dialogs", "roles", "createdDialogs", "inFriendRequests", "outFriendRequests", "friends" })
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 4, max = 20)
    private final String username;
    private String firstName;
    private String lastName;
    @Email
    private final String email;
    @Size(min = 8, max = 32)
    private String password;
    private boolean isActivated;
    private String activationCode;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Dialog> createdDialogs;
    @ManyToMany(fetch=FetchType.EAGER, mappedBy = "users", cascade = CascadeType.MERGE)
    private Set<Dialog> dialogs;

    @ManyToMany(fetch=FetchType.EAGER)
    private Set<User> inFriendRequests;
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<User> outFriendRequests;
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<User> friends;

    public User() {
        this.username = "username";
        this.email = "email";
        this.password = "password";
    }
    public User(String username, String email, String firstName, String lastName, String password, String activationCode, Role r) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.isActivated = false;
        this.activationCode = activationCode;
        this.roles = new HashSet<>();
        this.roles.add(r);
        this.createdDialogs = new HashSet<>();
        this.dialogs = new HashSet<>();
        this.inFriendRequests = new HashSet<>();
        this.outFriendRequests = new HashSet<>();
        this.friends = new HashSet<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActivated;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        return Objects.equals(this.hashCode(), other.hashCode());
    }

    @Override
    public int hashCode() {
        return this.username.hashCode() ;
    }

    public boolean equals(User other) {
        return Objects.equals(this.username, other.username);
    }

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public void setEnabled() {
        this.isActivated = true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role r) {
        roles.add(r);
    }

    public Set<Dialog> getCreatedDialogs() {
        return createdDialogs;
    }

    public void addCreatedDialogs(Dialog d) {
        if(this.createdDialogs.contains(d)) {
            return;
        }
        this.createdDialogs.add(d);
    }

    public void removeCreatedDialogs(Dialog d) {
        if(!this.createdDialogs.contains(d)) {
            return;
        }
        this.createdDialogs.remove(d);
    }

    public Set<Dialog> getDialogs() {
        return dialogs;
    }

    public void addMembershipDialogs(Dialog d) {
        if(this.dialogs.contains(d)) {
            return;
        }
        this.dialogs.add(d);
        d.addUser(this);
    }

    public void removeMembershipDialogs(Dialog d) {
        if(!this.dialogs.contains(d)) {
            return;
        }
        this.dialogs.remove(d);
        d.removeUser(this);
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void addFriend(User u) {
        if(this.friends.contains(u)) {
            return;
        }
        this.friends.add(u);
        u.addFriend(this);
    }

    public void removeFriend(User u) {
        if(!this.friends.contains(u)) {
            return;
        }
        this.friends.remove(u);
        u.removeFriend(this);
    }

    public Set<User> getInFriendRequests() {
        return inFriendRequests;
    }

    public void addInFriendRequests(User u) {
        if(this.inFriendRequests.contains(u) || this.friends.contains(u) || this.outFriendRequests.contains(u)) {
            return;
        }
        this.inFriendRequests.add(u);
        u.addOutFriendRequests(this);
    }

    public void removeInFriendRequests(User u) {
        if(!this.inFriendRequests.contains(u)) {
            return;
        }
        this.inFriendRequests.remove(u);
        u.removeOutFriendRequests(this);
    }

    public Set<User> getOutFriendRequests() {
        return outFriendRequests;
    }

    public void addOutFriendRequests(User u) {
        if(this.outFriendRequests.contains(u) || this.inFriendRequests.contains(u) || this.friends.contains(u)) {
            return;
        }
        this.outFriendRequests.add(u);
        u.addInFriendRequests(this);
    }

    public void removeOutFriendRequests(User u) {
        if(!this.outFriendRequests.contains(u)) {
            return;
        }
        this.outFriendRequests.remove(u);
        u.removeInFriendRequests(this);
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
}
