package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
