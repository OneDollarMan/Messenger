package rgr.Messenger.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rgr.Messenger.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
