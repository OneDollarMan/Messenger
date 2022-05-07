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
    @ManyToMany(mappedBy = "membershipDialogs")
    private final Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    private final Set<Message> messages;

    public Dialog() {
        this.users = new HashSet<>();
        this.messages = new HashSet<>();
    }
    public Dialog(User u) {
        this.creator = u;
        this.users = new HashSet<>();
        users.add(u);
        this.messages = new HashSet<>();
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message m) {
        this.messages.add(m);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User u) {
        this.users.add(u);
    }

    public void removeUser(User u) {
        this.users.remove(u);
    }

    public Long getId() {
        return id;
    }

    public User getCreator() {
        return creator;
    }
}
