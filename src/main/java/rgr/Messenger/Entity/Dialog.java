package rgr.Messenger.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User creator;
    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<User> users;

    @OneToMany(fetch = FetchType.EAGER)
    @OrderBy("date")
    private final Set<Message> messages;

    public Dialog() {
        this.users = new HashSet<>();
        this.messages = new HashSet<>();
    }
    public Dialog(User u) {
        this.creator = u;
        u.addCreatedDialogs(this);
        this.users = new HashSet<>();
        this.messages = new HashSet<>();
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message m) {
        this.messages.add(m);
    }

    public void removeMessage(Message m) {
        if(!this.messages.contains(m)) {
            return;
        }
        this.messages.remove(m);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User u) {
        if(this.users.contains(u)) {
            return;
        }
        this.users.add(u);
        u.addMembershipDialogs(this);
    }

    public void removeUser(User u) {
        if(!this.users.contains(u)) {
            return;
        }
        this.users.remove(u);
        u.removeMembershipDialogs(this);
    }

    public Long getId() {
        return id;
    }

    public User getCreator() {
        return creator;
    }
}
