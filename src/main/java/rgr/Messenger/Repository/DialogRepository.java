package rgr.Messenger.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rgr.Messenger.Entity.Dialog;
import rgr.Messenger.Entity.User;

import java.util.Optional;
import java.util.Set;

public interface DialogRepository extends JpaRepository<Dialog, Long> {
    Set<Dialog> findAllByUsers(User u);
    Optional<Dialog> findByCreatorAndId(User u, Long id);
}
