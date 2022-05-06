package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
                model.addAttribute("message", "Вы успешно зарегистрировались! Для продолжения активируйте учетную запись с помощю ссылки, отправленной на вашу почту.");
                return "login";
            } else {
                return "register";
            }
        } else {
            return "register";
        }
    }
}
