package rgr.Messenger.Service;

import java.util.Optional;
import java.util.Set;
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
    UserRepository us;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> u = us.findByUsername(username);
        if(u.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        } else {
            return u.get();
        }

    }

    public boolean isUserRegistered(String username) {
        return us.findByUsername(username).isPresent();
    }

    public boolean isUsernameFree(String username) {
        return us.findByUsername(username).isEmpty();
    }

    public boolean isEmailFree(String email) {
        return us.findByEmail(email) == null;
    }

    public Role getAdminRole() {
        return roleRepository.findByName("ROLE_ADMIN");
    }

    public boolean isUserAdmin(User u) {
        return u.getAuthorities().contains(roleRepository.findByName("ROLE_ADMIN"));
    }

    public boolean registerUser(String username, String firstName, String lastName, String email, String password) {
        if(isUsernameFree(username) && isEmailFree(email)) {
            User u = new User(username, email, firstName, lastName, bCryptPasswordEncoder.encode(password), UUID.randomUUID().toString(), roleRepository.findByName("ROLE_USER"));
            if(u.getUsername().equals("admin")) {
                u.addRole(roleRepository.findByName("ROLE_ADMIN"));
            }
            us.save(u);
            mailService.sendGreetingMessage(u);
            return true;
        } else {
            return false;
        }
    }

    public boolean activateUser(String activationCode) {
        Optional<User> u = us.findByActivationCode(activationCode);
        if(u.isPresent()) {
            User user = u.get();
            user.setActivationCode(null);
            user.setEnabled();
            us.save(user);
            return true;
        }
        return false;
    }

    public Optional<User> findUserById(Long id) {
        return us.findById(id);
    }

    public User findUserByUsername(String username) {
        return us.findByUsername(username).orElse(null);
    }

    public void addRole(String username, String name) {
        Optional<User> user = us.findByUsername(username);
        Role r = roleRepository.findByName(name);
        if(user.isPresent() && r != null) {
            User u = user.get();
            u.addRole(r);
            us.save(u);
        }
    }

    public void recoverPassword(String username) {
        User user = findUserByUsername(username);
        if(user != null) {
            if(user.isEnabled()) {
                user.setActivationCode(UUID.randomUUID().toString());
                us.save(user);
                mailService.sendRecoverMessage(user);
            }
        }
    }

    public boolean isUserFoundByCode(String code) {
        return us.findByActivationCode(code).isPresent();
    }

    public boolean changePassword(String code, String newPassword) {
        Optional<User> user = us.findByActivationCode(code);
        if(user.isPresent()) {
            user.get().setPassword(bCryptPasswordEncoder.encode(newPassword));
            us.save(user.get());
            return true;
        }
        return false;
    }

    public Set<User> getFriends(User u) {
        return u.getFriends();
    }

    public void removeFriend(User u, Long id) {
        Optional<User> opt = us.findById(id);
        if(opt.isPresent()) {
            User user = opt.get();
            u.removeFriend(user);
            us.save(user);
            us.save(u);
        }
    }

    public void sendFriendRequest(User u, String username) {
        Optional<User> opt = us.findByUsername(username);
        if(opt.isPresent()) {
            User user = opt.get();
            if(u.getInFriendRequests().contains(user)) {
                u.removeInFriendRequests(user);
                u.addFriend(user);
            } else {
                u.addOutFriendRequests(user);
            }
            us.save(user);
            us.save(u);
        }
    }

    public void removeFriendRequest(User u, Long id) {
        Optional<User> opt = us.findById(id);
        if(opt.isPresent()) {
            User user = opt.get();
            u.removeOutFriendRequests(user);
            us.save(user);
            us.save(u);
        }
    }

    public void acceptFriendRequest(User u, Long id) {
        Optional<User> opt = us.findById(id);
        if(opt.isPresent()) {
            User user = opt.get();
            u.removeInFriendRequests(user);
            u.addFriend(user);
            us.save(user);
            us.save(u);
        }
    }

    public void declineFriendRequest(User u, Long id) {
        Optional<User> opt = us.findById(id);
        if(opt.isPresent()) {
            User user = opt.get();
            u.removeInFriendRequests(user);
            us.save(user);
            us.save(u);
        }
    }

    public void changeFirstName(User u, String firstName) {
        u.setFirstName(firstName);
        us.save(u);
    }

    public void changeLastName(User u, String lastName) {
        u.setLastName(lastName);
        us.save(u);
    }
}
