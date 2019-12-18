package de.fasterfood.fasterfood.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.naming.AuthenticationException;
import java.util.Optional;


@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public int registerUser(RegisterUserDTO registerUserDTO) throws AuthenticationException {

        Optional<UserEntity> optionalUser = userRepository.findByUsername(registerUserDTO.getUsername());

        if (!optionalUser.isPresent()) {
            UserEntity regUserEntity = new UserEntity(registerUserDTO.getUsername(), passwordEncoder.encode(registerUserDTO.getPassword()));
            userRepository.save(regUserEntity);
            return 1;
        } else {
            return 2;
        }
    }
}

