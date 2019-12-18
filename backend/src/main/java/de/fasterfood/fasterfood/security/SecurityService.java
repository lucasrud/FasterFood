package de.fasterfood.fasterfood.security;

//import de.fasterfood.fasterfood.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Optional;


@Service
public class SecurityService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
//    private UserRepository userRepository;

    @Autowired
    public SecurityService(PasswordEncoder passwordEncoder /*, UserRepository userRepository*/) {
        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);

        // TODO: Nutzer aus Datenbank laden



        if(username.equalsIgnoreCase("philip")) {
            return new User("philip", this.passwordEncoder.encode("hallo"), Arrays.asList());
        }

        throw new UsernameNotFoundException("nö");
    }
}
