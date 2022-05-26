package rgr.Messenger.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rgr.Messenger.Entity.Dialog;
import rgr.Messenger.Entity.Message;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Repository.DialogRepository;
import rgr.Messenger.Repository.MessageRepository;
import rgr.Messenger.Repository.UserRepository;

import java.util.*;

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
            Optional<Dialog> d1 = dr.findByCreatorAndSecondUser(u, user.get());
            Optional<Dialog> d2 = dr.findByCreatorAndSecondUser(user.get(), u);
            if(d1.isPresent()) {
                Dialog dg = d1.get();
                dg.addUser(u);
                dr.save(dg);
                return dg;
            } else if(d2.isPresent()) {
                Dialog dg = d2.get();
                dg.addUser(u);
                dr.save(dg);
                return dg;
            }
            return createDialog(u, id);
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

    public Map<String, Message> getMessagesOfDialog(User u, Long id) {
        Optional<Dialog> d = dr.findById(id);
        if(d.isPresent()) {
            if(d.get().getUsers().contains(u)) {
                Map<String, Message> map = new HashMap<>();
                d.get().getMessages().forEach(el -> map.put(String.valueOf(el.getId()), el));
                return map;
            }
        }
        return null;
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

    public void createRoom(User u, String title) {
        Dialog d = new Dialog(u);
        d.setRoom(true);
        d.addUser(u);
        d.setTitle(title);
        dr.save(d);
    }

    public Set<Dialog> getAllRooms() {
        return dr.findAllByIsRoom(true);
    }

    public Set<Dialog> getRoomsOfUser(User u) {
        return dr.findAllByIsRoomAndUsers(true, u);
    }

    public Dialog getRoom(Long id) {
        return dr.findById(id).orElseGet(null);
    }
}
