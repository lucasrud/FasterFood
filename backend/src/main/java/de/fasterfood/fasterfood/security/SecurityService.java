package de.fasterfood.fasterfood.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SecurityService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        // TODO: Nutzer aus Datenbank laden


        if(username.equalsIgnoreCase("philip")) {
            return new User("philip", this.passwordEncoder.encode("hallo"), List.of());
        }

        throw new UsernameNotFoundException("nö");
    }
}
