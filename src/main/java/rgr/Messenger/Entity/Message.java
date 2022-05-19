package rgr.Messenger.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    @ManyToOne
    private final User sender;
    @ManyToOne
    private final User receiver;
    @ManyToOne
    private final Dialog dialog;
    private final String text;

    public Message() {
        this.sender = null;
        this.receiver = null;
        this.dialog = null;
        this.text = "";
    }

    public Message(User s, User r, Dialog d, String text) {
        this.sender = s;
        this.receiver = r;
        this.dialog = d;
        this.text = text;
        this.date = new Date();
        d.addMessage(this);
    }

    public String getText() {
        return text;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public Date getDate() { return date; }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }
}
