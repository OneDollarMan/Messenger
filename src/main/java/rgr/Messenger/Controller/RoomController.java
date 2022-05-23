package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Service.MessengerService;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private MessengerService ms;

    @GetMapping
    public String rooms(@AuthenticationPrincipal User u, Model model) {
        model.addAttribute("open_rooms", ms.getAllRooms());
        model.addAttribute("joined_rooms", ms.getRoomsOfUser(u));
        return "rooms";
    }

    @PostMapping("/create")
    public String createRoom(@AuthenticationPrincipal User u, @RequestParam String title) {
        ms.createRoom(u, title);
        return "redirect:/rooms";
    }

    @GetMapping("{id}")
    public String room(@AuthenticationPrincipal User u, Model m, @PathVariable Long id) {
        m.addAttribute("dialog", ms.getRoom(id));
        return "room";
    }

}
