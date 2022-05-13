package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Service.UserService;

@Controller

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model) {
        if(username != null && email != null && password != null) {
            if(userService.registerUser(username, email, password)) {
                model.addAttribute("message", "Вы успешно зарегистрировались! Для продолжения активируйте учетную запись с помощью ссылки, отправленной на вашу почту.");
                return "redirect:/login";
            } else {
                return "register";
            }
        } else {
            return "register";
        }
    }

    @GetMapping("/activate/{activationCode}")
    public String activateUser(Model model, @PathVariable String activationCode) {
        if(userService.activateUser(activationCode)) {
            model.addAttribute("message", "Пользователь активирован");
        } else {
            model.addAttribute("message", "Код активации не найден");
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal User u) {
        return "profile";
    }

}
