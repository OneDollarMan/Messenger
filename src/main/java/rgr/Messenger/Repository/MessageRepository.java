package rgr.Messenger.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rgr.Messenger.Entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
