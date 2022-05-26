package rgr.Messenger.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    @ManyToOne
    private User creator;
    @ManyToOne
    private User secondUser;

    @OneToMany(fetch = FetchType.EAGER)
    @OrderBy("date DESC")
    private Set<Message> messages;

    @ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<User> users;

    private boolean isRoom;

    private boolean isClosed;

    public Dialog() {
        this.messages = new HashSet<>();
    }

    public Dialog(User u) {
        this.isRoom = false;
        this.isClosed = false;
        this.creator = u;
        u.addCreatedDialogs(this);
        this.messages = new HashSet<>();
        this.users = new HashSet<>();
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

    public void clearMessages() {
        this.messages.clear();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    public User getCreator() {
        return creator;
    }

    public User getSecondUser() { return secondUser; }

    public void setSecondUser(User u) { this.secondUser = u; }

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

    public boolean isRoom() {
        return isRoom;
    }

    public void setRoom(boolean room) {
        isRoom = room;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
