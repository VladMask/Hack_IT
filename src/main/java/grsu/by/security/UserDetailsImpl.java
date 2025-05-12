package grsu.by.security;

import grsu.by.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import grsu.by.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Set<UserRole> userRoles;

    public UserDetailsImpl(User user){
        this.email = user.getCredentials().getEmail();
        this.password = user.getCredentials().getPassword();
        this.userRoles = user.getUserRoles();
        this.id = user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles.stream().map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getName())).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
