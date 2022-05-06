package rgr.Messenger.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import rgr.Messenger.Entity.User;

@Service
public class MailService {

    @Autowired
    public JavaMailSender emailSender;

    public void send(String mailTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);
        this.emailSender.send(message);
    }

    public void sendGreetingMessage(User u) {
        send(u.getEmail(), "Система тестирования: успешная регистрация", "Здравствуйте, " + u.getUsername() + "! Вы успешно зарегистрировались в системе тестирования. Для активации учетной записи перейдите по ссылке http://localhost:8080/activate/" + u.getActivationCode());
    }

    public void sendRecoverMessage(User u) {
        send(u.getEmail(), "Система тестирования: сменя пароля", "Здравствуйте! Для смены пароля проследуйте по ссылке: http://localhost:8080/recoverPassword/" + u.getActivationCode());
    }

}

