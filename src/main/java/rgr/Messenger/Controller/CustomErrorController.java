package rgr.Messenger.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String errorPage(HttpServletRequest request, Model model) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("lang/messages", locale);

        if(status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("error", bundle.getString("error.notFound"));
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("error", bundle.getString("error.forbidden"));
            }
            else {
                model.addAttribute("error", bundle.getString("error"));
            }
        }
        return "error";
    }

}
