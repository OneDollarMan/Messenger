package rgr.Messenger.Service;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import rgr.Messenger.Entity.User;
import rgr.Messenger.Entity.Role;
import rgr.Messenger.Repository.UserRepository;
import rgr.Messenger.Repository.RoleRepository;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> u = userRepository.findByUsername(username);
        if(u.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        } else {
            return u.get();
        }

    }

    public boolean isUserRegistered(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isUsernameFree(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public boolean isEmailFree(String email) {
        return userRepository.findByEmail(email) == null;
    }

    public Role getAdminRole() {
        return roleRepository.findByName("ROLE_ADMIN");
    }

    public boolean isUserAdmin(User u) {
        return u.getAuthorities().contains(roleRepository.findByName("ROLE_ADMIN"));
    }

    public boolean registerUser(String username, String email, String password) {
        if(isUsernameFree(username) && isEmailFree(email)) {
            User u = new User(username, email, bCryptPasswordEncoder.encode(password), UUID.randomUUID().toString(), roleRepository.findByName("ROLE_USER"));
            if(u.getUsername().equals("admin")) {
                u.addRole(roleRepository.findByName("ROLE_ADMIN"));
            }
            userRepository.save(u);
            mailService.sendGreetingMessage(u);
            return true;
        } else {
            return false;
        }
    }

    public boolean activateUser(String activationCode) {
        Optional<User> u = userRepository.findByActivationCode(activationCode);
        if(u.isPresent()) {
            User user = u.get();
            user.setActivationCode(null);
            user.setEnabled();
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void addRole(String username, String name) {
        Optional<User> user = userRepository.findByUsername(username);
        Role r = roleRepository.findByName(name);
        if(user.isPresent() && r != null) {
            User u = user.get();
            u.addRole(r);
            userRepository.save(u);
        }
    }

    public void recoverPassword(String username) {
        User user = findUserByUsername(username);
        if(user != null) {
            if(user.isEnabled()) {
                user.setActivationCode(UUID.randomUUID().toString());
                userRepository.save(user);
                mailService.sendRecoverMessage(user);
            }
        }
    }

    public boolean isUserFoundByCode(String code) {
        return userRepository.findByActivationCode(code).isPresent();
    }

    public boolean changePassword(String code, String newPassword) {
        Optional<User> user = userRepository.findByActivationCode(code);
        if(user.isPresent()) {
            user.get().setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

}
