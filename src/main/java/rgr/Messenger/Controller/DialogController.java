package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rgr.Messenger.Entity.Dialog;
import rgr.Messenger.Entity.Message;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Service.MessengerService;
import rgr.Messenger.Service.UserService;

import java.util.Map;

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
        return "redirect:/dialogs/" + id;
    }

    @GetMapping("/rm/{id}")
    public String dialogsRemove(@AuthenticationPrincipal User u, @PathVariable String id, RedirectAttributes ra) {
        if (!ms.removeDialog(u, id)) {
            ra.addAttribute("message", "Ошибка доступа");
        }
        return "redirect:/dialogs";

    }

    @GetMapping("{id}")
    public String dialog(@AuthenticationPrincipal User u,  @PathVariable Long id, Model model, RedirectAttributes ra) {
        if(!u.getId().equals(id)) {
            Dialog d = ms.getDialog(u, id);
            if(d != null) {
                model.addAttribute("messages", d.getMessages());
                model.addAttribute("dialog", d);
                return "dialog";
            }
        }
        ra.addFlashAttribute("message", "Вы не можете писать самому себе");
        return "redirect:/dialogs";
    }

    @PostMapping("/sendMessage/{id}")
    public String sendMessage(@AuthenticationPrincipal User u,  @PathVariable Long id, @RequestParam String message) {
        ms.sendMessage(u, id, message);
        return "redirect:/dialogs/";
    }

    @GetMapping("/leave/{id}")
    public String leaveDialog(@AuthenticationPrincipal User u,  @PathVariable Long id) {
        ms.leaveDialog(u, id);
        return "redirect:/dialogs";
    }

}
