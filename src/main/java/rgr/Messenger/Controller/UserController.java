package rgr.Messenger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rgr.Messenger.Entity.User;
import rgr.Messenger.Service.UserService;

import java.util.Optional;

@Controller

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password, RedirectAttributes ra) {
        if(username != null && email != null && firstName != null && lastName != null && password != null) {
            if(userService.registerUser(username, firstName, lastName, email, password)) {
                ra.addFlashAttribute("message", "Вы успешно зарегистрировались! Для продолжения активируйте учетную запись с помощью ссылки, отправленной на вашу почту.");
                return "redirect:/login";
            } else {
                ra.addFlashAttribute("message", "Пользователь с таким ником или почтой уже существует");
                return "register";
            }
        } else {
            ra.addFlashAttribute("message", "Заполните поля");
            return "register";
        }
    }

    @GetMapping("/activate/{activationCode}")
    public String activateUser(Model model, @PathVariable String activationCode, RedirectAttributes ra) {
        if(userService.activateUser(activationCode)) {
            ra.addFlashAttribute("message", "Пользователь активирован");
        } else {
            ra.addFlashAttribute("message", "Код активации не найден");
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal User u) {
        return "profile";
    }

    @GetMapping("/profile/{id}")
    public String profileUser(@AuthenticationPrincipal User u, @PathVariable Long id, Model model) {
        Optional<User> user = userService.findUserById(id);
        if(user.isPresent()) {
            model.addAttribute("u", user.get());
            return "profile";
        } else {
            return "redirect:/profile";
        }
    }

    @PostMapping("/changeFirstName")
    public String changeFirstName(@AuthenticationPrincipal User u, @RequestParam String firstName) {
        if(firstName != null) {
            userService.changeFirstName(u, firstName);
        }
        return "redirect:/profile";
    }

    @PostMapping("/changeLastName")
    public String changeLastName(@AuthenticationPrincipal User u, @RequestParam String lastName) {
        if(lastName != null) {
            userService.changeLastName(u, lastName);
        }
        return "redirect:/profile";
    }

}
