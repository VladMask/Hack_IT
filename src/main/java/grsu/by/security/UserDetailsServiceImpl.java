package grsu.by.security;

import lombok.RequiredArgsConstructor;
import grsu.by.entity.User;
import grsu.by.exception.EntityNotFoundException;
import grsu.by.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(
                        Map.of(
                                "class", User.class.getName(),
                                "email", email,
                                "date", Instant.now().toString()
                        )
                )
        );


        return UserDetailsImpl.builder()
                .id(user.getId())
                .email(user.getCredentials().getEmail())
                .password(user.getCredentials().getPassword())
                .userRoles(user.getUserRoles())
                .build();
    }

    public Long getUserIdFromSecurityContext(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        return userDetails.getId();
    }
}
