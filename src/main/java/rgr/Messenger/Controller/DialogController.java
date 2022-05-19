package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rgr.Messenger.Entity.Dialog;
import rgr.Messenger.Entity.Message;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Service.MessengerService;
import rgr.Messenger.Service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/dialogs")
public class DialogController {

    @Autowired
    private MessengerService ms;

    @Autowired
    private UserService us;

    @GetMapping
    public String dialogs(@AuthenticationPrincipal User u, Model model) {
        model.addAttribute("dialogs", ms.getDialogsOfUser(u));
        model.addAttribute("friends", us.getFriends(u));
        return "dialogs";
    }

    @PostMapping("/create")
    public String dialogsCreate(@AuthenticationPrincipal User u, Model model, @RequestParam Long id) {
        //Long did = ms.createDialog(u, id);
        return "redirect:/dialogs/";
    }

    @GetMapping("/rm/{id}")
    public String dialogsRemove(@AuthenticationPrincipal User u, @PathVariable String id, Model m) {
        if (!ms.removeDialog(u, id)) {
            m.addAttribute("message", "Ошибка");
        }
        return "redirect:/dialogs";

    }

    @GetMapping("{id}")
    public String dialog(@AuthenticationPrincipal User u,  @PathVariable Long id, Model model) {
        if(!u.getId().equals(id)) {
            Dialog d = ms.getDialog(u, id);
            if(d != null) {
                model.addAttribute("messages", d.getMessages());
                model.addAttribute("dialog", d);
                return "dialog";
            }
        }
        return "redirect:/dialogs";
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(@AuthenticationPrincipal User u, Message m) {
        ms.saveMessage(m);
    }

    @GetMapping("/leave/{id}")
    public String leaveDialog(@AuthenticationPrincipal User u,  @PathVariable Long id) {
        ms.leaveDialog(u, id);
        return "redirect:/dialogs";
    }

    @PostMapping("/invite/{id}")
    public String inviteUser(@AuthenticationPrincipal User u,  @PathVariable Long id, @RequestParam String username) {
        ms.addUserToDialog(u, username, id);
        return "redirect:/dialogs/" + id;
    }
}
