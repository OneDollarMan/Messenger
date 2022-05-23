package rgr.Messenger.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"dialog"})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    @ManyToOne
    private final User author;
    @ManyToOne
    private final Dialog dialog;
    private final String text;

    public Message() {
        this.author = null;
        this.dialog = null;
        this.text = "";
    }

    public Message(User u, Dialog d, String text) {
        this.author = u;
        this.dialog = d;
        this.text = text;
        this.date = new Date();
        d.addMessage(this);
    }

    public Long getId() {
        return id;
    }
    public String getText() {
        return text;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public Date getDate() { return date; }

    public User getAuthor() {
        return author;
    }
}
