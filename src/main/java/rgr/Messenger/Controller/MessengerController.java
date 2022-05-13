package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rgr.Messenger.Entity.Dialog;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Service.MessengerService;
import rgr.Messenger.Service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/dialogs")
public class MessengerController {

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
        Optional<Dialog> d = ms.getDialog(u, id);
        Dialog dialog = d.orElseGet(() -> ms.createDialog(u, id));
        model.addAttribute("messages", dialog.getMessages());
        model.addAttribute("dialog", dialog);
        return "dialog";
    }

    @PostMapping("/sendMessage/{id}")
    public String sendMessage(@AuthenticationPrincipal User u,  @PathVariable Long id, @RequestParam String message) {
        ms.sendMessage(u, id, message);
        return "redirect:/dialogs/" + id;
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
