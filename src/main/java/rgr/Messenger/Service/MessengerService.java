package rgr.Messenger.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rgr.Messenger.Entity.Dialog;
import rgr.Messenger.Entity.Message;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Repository.DialogRepository;
import rgr.Messenger.Repository.MessageRepository;
import rgr.Messenger.Repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MessengerService {

    @Autowired
    private DialogRepository dr;
    @Autowired
    private MessageRepository mr;
    @Autowired
    private UserRepository ur;

    private Dialog createDialog(User u, Long id) {
        Optional<User> secondUser = ur.findById(id);
        if(secondUser.isPresent()) {
            Dialog d = new Dialog(u);
            d.addUser(u);
            d.addUser(secondUser.get());
            d.setSecondUser(secondUser.get());
            dr.save(d);
            return d;
        }
        return null;
    }

    public Dialog getDialog(User u, Long id) {
        Optional<User> user = ur.findById(id);
        if(user.isPresent()) {
            Set<User> s = new HashSet<>();
            s.add(u);
            s.add(user.get());
            return dr.findByUsersIn(s).orElseGet(() -> createDialog(u, id));
        }
        return null;
    }

    public void leaveDialog(User u, Long id) {
        Optional<Dialog> d = dr.findById(id);
        if(d.isPresent()) {
            Dialog dialog = d.get();
            dialog.removeUser(u);
            dr.save(dialog);
        }
    }

    public void addUserToDialog(User user, String username, Long did) {
        Optional<User> u = ur.findByUsername(username);
        Optional<Dialog> d = dr.findById(did);
        if(u.isPresent() && d.isPresent()) {
            Dialog dialog = d.get();
            if(dialog.getUsers().contains(user)) {
                dialog.addUser(u.get());
                dr.save(d.get());
            }
        }
    }

    public boolean removeDialog(User u, String id) {
        Optional<Dialog> d = dr.findByCreatorAndId(u, Long.parseLong(id));
        if(d.isPresent()) {
            Dialog dialog = d.get();
            dialog.getCreator().removeCreatedDialogs(dialog);
            for(Message m : dialog.getMessages()) {
                mr.delete(m);
            }
            dialog.clearMessages();
            dr.save(dialog);
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

    public Set<Dialog> getAllRooms() {
        return mr.findAllByIsRoom(true);
    }
}
