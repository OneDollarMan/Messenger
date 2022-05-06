package rgr.Messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"rgr.Messenger.Controller", "rgr.Messenger.Configuration", "rgr.Messenger.Service"})
@EnableJpaRepositories(basePackages ={"rgr.Messenger.Repository"})
@EntityScan(basePackages ={"rgr.Messenger.Entity"})
public class MessengerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessengerApplication.class, args);
	}

}
