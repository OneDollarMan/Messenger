package rgr.Messenger.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import rgr.Messenger.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
    Optional<User> findByActivationCode(String activationCode);
}
