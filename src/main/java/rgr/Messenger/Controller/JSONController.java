package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rgr.Messenger.Entity.Message;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Service.MessengerService;

import java.util.Map;

@RestController
public class JSONController {

    @Autowired
    private MessengerService ms;

    @GetMapping("/dialogs/getMessages/{id}")
    public Map<String, Message> getMessages(@AuthenticationPrincipal User u, @PathVariable long id) {
        return ms.getMessagesOfDialog(u, id);
    }
}
