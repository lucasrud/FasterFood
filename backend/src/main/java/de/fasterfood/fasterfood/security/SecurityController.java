package de.fasterfood.fasterfood.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;


@RestController
public class SecurityController {


    private UserRepository userRepository;

    @Autowired
    public SecurityController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/api/sessionUser")
    public UserDTO sessionUser(@AuthenticationPrincipal UserDetails userDetails) {
        return new UserDTO(userDetails.getUsername());
    }

    @PostMapping("/api/register/user")
    public User registerNewUser(@RequestBody RegisterUserDTO registerUserDTO) {

        Optional<User> optionalUser = userRepository.findByUsername(registerUserDTO.getUsername());
        if (optionalUser.isPresent()) {
            // User ist vorhanden
        }


        return null;
    }


}
