package rgr.Messenger.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rgr.Messenger.Entity.Dialog;
import rgr.Messenger.Entity.Message;

import java.util.Set;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Set<Dialog> findAllByIsRoom(boolean b);
}
