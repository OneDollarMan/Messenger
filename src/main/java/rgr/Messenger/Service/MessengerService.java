package rgr.Messenger.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rgr.Messenger.Entity.Dialog;
import rgr.Messenger.Entity.Message;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Repository.DialogRepository;
import rgr.Messenger.Repository.MessageRepository;
import rgr.Messenger.Repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class MessengerService {

    @Autowired
    private DialogRepository dr;
    @Autowired
    private MessageRepository mr;

    public void createDialog(User u) {
        Dialog d = new Dialog(u);
        d.addUser(u);
        dr.save(d);
    }

    public Dialog getDialog(Long id) {
        return dr.findById(id).orElse(null);
    }

    public boolean removeDialog(User u, String id) {
        Optional<Dialog> d = dr.findByCreatorAndId(u, Long.parseLong(id));
        if(d.isPresent()) {
            Dialog dialog = d.get();
            for(User user : dialog.getUsers()) {
                user.removeMembershipDialogs(dialog);
            }
            dialog.getCreator().removeCreatedDialogs(dialog);
            for(Message m : dialog.getMessages()) {
                dialog.removeMessage(m);
                mr.delete(m);
            }
            dr.delete(dialog);
            return true;
        } else {
            return false;
        }
    }

    public Set<Dialog> getDialogsOfUser(User u) {
        return dr.findAllByUsers(u);
    }

    public Set<Message> getMessagesOfDialog(Long id) {
        Optional<Dialog> d = dr.findById(id);
        return d.map(Dialog::getMessages).orElse(null);
    }

    public void sendMessage(User u, Long id, String message) {
        Optional<Dialog> d = dr.findById(id);
        if(d.isPresent()) {
            Dialog dialog = d.get();
            if(dialog.getUsers().contains(u)) {
                Message m = new Message(u, dialog, message);
                dialog.addMessage(m);
                mr.save(m);
            }
        }
    }
}
