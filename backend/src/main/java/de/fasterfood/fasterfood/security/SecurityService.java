package de.fasterfood.fasterfood.security;

import de.fasterfood.fasterfood.user.UserEntity;
import de.fasterfood.fasterfood.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;


@Service
public class SecurityService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public SecurityService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        // TODO: Nutzer aus Datenbank laden
        Optional<UserEntity> user = userRepository.findByUsername(username);



        if(username.equalsIgnoreCase(user.get().getUsername())) {
            return new User(user.get().getUsername(), user.get().getPassword(), List.of());
        }

        throw new UsernameNotFoundException("User not found");
    }
}
