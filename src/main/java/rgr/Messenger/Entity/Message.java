package rgr.Messenger.Entity;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
    }

    public String getText() {
        return text;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public User getAuthor() {
        return author;
    }
}
