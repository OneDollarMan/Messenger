package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Service.MessengerService;

@Controller
@RequestMapping("/dialogs")
public class MessengerController {

    @Autowired
    MessengerService ms;

    @GetMapping
    public String dialogs(@AuthenticationPrincipal User u, Model model) {
        model.addAttribute("dialogs", ms.getDialogsOfUser(u));
        return "dialogs";
    }

    @GetMapping("/create")
    public String dialogsCreate(@AuthenticationPrincipal User u, Model model) {
        ms.createDialog(u);
        return "redirect:/dialogs";
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
        model.addAttribute("messages", ms.getMessagesOfDialog(id));
        model.addAttribute("dialog", ms.getDialog(id));
        return "dialog";
    }

    @PostMapping("/sendMessage/{id}")
    public String sendMessage(@AuthenticationPrincipal User u,  @PathVariable Long id, @RequestParam String message) {
        ms.sendMessage(u, id, message);
        return "redirect:/dialogs/" + id;
    }

}
