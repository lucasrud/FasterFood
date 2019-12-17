package de.fasterfood.fasterfood.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;


@RestController
public class SecurityController {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/api/sessionUser")
    public UserDTO sessionUser(@AuthenticationPrincipal UserDetails userDetails) {
        return new UserDTO(userDetails.getUsername());
    }

    @PostMapping("/api/register/user")
    public User registerNewUser(@RequestBody RegisterUserDTO registerUserDTO) {

        Optional<User> optionalUser = userRepository.findByUsername(registerUserDTO.getUsername());
        User regUser = new User(null,null);

        if (!optionalUser.isPresent()) {
            regUser = new User(registerUserDTO.getUsername(), passwordEncoder.encode(registerUserDTO.getPassword()));
            userRepository.save(regUser);
        }

        return regUser;
    }


}
