package rgr.Messenger.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rgr.Messenger.Entity.Dialog;
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
    @Autowired
    private UserRepository ur;

    public void createDialog(User u) {
        Dialog d = new Dialog(u);
        d.addUser(u);
        dr.save(d);
    }

    public boolean removeDialog(User u, String id) {
        Optional<Dialog> d = dr.findByCreatorAndId(u, Long.parseLong(id));
        if(d.isPresent()) {
            Dialog dialog = d.get();
            for(User user : dialog.getUsers()) {
                user.removeMembershipDialogs(dialog);
            }
            dialog.getCreator().removeCreatedDialogs(dialog);
            dr.delete(dialog);
            return true;
        } else {
            return false;
        }
    }

    public Set<Dialog> getDialogsOfUser(User u) {
        return dr.findAllByUsers(u);
    }

    public void sendMessage(User u, String dialogId, String message) {

    }
}
