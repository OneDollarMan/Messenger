package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Service.UserService;

@Controller
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private UserService us;

    @GetMapping
    public String friends(@AuthenticationPrincipal User u) {
        return "friends";
    }

    @PostMapping("/sendFriendRequest")
    public String sendFriendRequest(@AuthenticationPrincipal User u, @RequestParam String username) {
        if(!u.getUsername().equals(username)) {
            us.sendFriendRequest(u, username);
        }
        return "redirect:/friends";
    }

    @GetMapping("/cancelFriendRequest/{id}")
    public String cancelFriendRequest(@AuthenticationPrincipal User u, @PathVariable Long id) {
        us.removeFriendRequest(u, id);
        return "redirect:/friends";
    }

    @GetMapping("/acceptFriendRequest/{id}")
    public String acceptFriendRequest(@AuthenticationPrincipal User u, @PathVariable Long id) {
        us.acceptFriendRequest(u, id);
        return "redirect:/friends";
    }

    @GetMapping("/declineFriendRequest/{id}")
    public String declineFriendRequest(@AuthenticationPrincipal User u, @PathVariable Long id) {
        us.declineFriendRequest(u, id);
        return "redirect:/friends";
    }

    @GetMapping("/removeFriend/{id}")
    public String removeFriend(@AuthenticationPrincipal User u, @PathVariable Long id) {
        us.removeFriend(u, id);
        return "redirect:/friends";
    }

}
