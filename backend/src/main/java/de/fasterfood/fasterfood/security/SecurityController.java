package de.fasterfood.fasterfood.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SecurityController {

    @GetMapping("/api/sessionUser")
    public UserDTO sessionUser(@AuthenticationPrincipal UserDetails userDetails) {
        return new UserDTO(userDetails.getUsername());
    }
}
