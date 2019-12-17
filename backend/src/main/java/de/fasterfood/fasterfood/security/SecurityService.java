package de.fasterfood.fasterfood.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;


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
            return new User("philip", this.passwordEncoder.encode("hallo"), Arrays.asList());
        }

        throw new UsernameNotFoundException("nö");
    }
}
