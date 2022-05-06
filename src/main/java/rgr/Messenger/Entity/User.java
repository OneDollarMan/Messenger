package rgr.Messenger.Entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 4, max = 20)
    private final String username;
    @Email
    private final String email;
    @Size(min = 8, max = 32)
    private final String password;
    private boolean isActivated;
    private final String activationCode;

    public User(String username, String email, String password, String activationCode) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActivated = false;
        this.activationCode = activationCode;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public boolean getActivated() {
        return isActivated;
    }

    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActivated;
    }

}
