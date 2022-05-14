package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Service.MessengerService;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private MessengerService ms;

    @GetMapping
    public String rooms(@AuthenticationPrincipal User u, Model model) {
        model.addAttribute("rooms", ms.getAllRooms());
        return "rooms";
    }

}
