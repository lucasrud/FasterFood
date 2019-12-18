package de.fasterfood.fasterfood.security;

import de.fasterfood.fasterfood.user.RegisterUserDTO;
import de.fasterfood.fasterfood.user.UserDTO;
import de.fasterfood.fasterfood.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;


@RestController
public class SecurityController {


    private UserService userService;

    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/api/sessionUser")
    public UserDTO sessionUser(@AuthenticationPrincipal UserDetails userDetails) {
//
//        System.out.println(userDetails.getUsername() + " test hier");
        if (userDetails == null) {
            return null;
        }
        return new UserDTO(userDetails.getUsername());
    }

    @PostMapping("/api/register/user")
    public int registerNewUser(@RequestBody RegisterUserDTO registerUserDTO) throws AuthenticationException {
        int testNumber = userService.registerUser(registerUserDTO);
        return testNumber;
    }

    @PostMapping("/api/logout")
    public void logoutUser(HttpSession session ) {
        session.invalidate();
    }


}
