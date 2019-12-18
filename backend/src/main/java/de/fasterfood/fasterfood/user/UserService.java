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

//    public List<UserMarkerDTO> getUserMarkerList() {
//        List<UserMarkerDTO> userMarkers = new LinkedList<>();
//        for (UserAccount user : userRepository.findAll()) {
//            int entryRequest = entryRepository.findEntriesByUser_IdAndType(user.getId(), "request").size();
//            int entryOffer = entryRepository.findEntriesByUser_IdAndType(user.getId(), "offer").size();
//            userMarkers.add(new UserMarkerDTO(user.getUsername(), user.getLongitude(), user.getLatitude(), entryRequest, entryOffer));
//        }
//        return userMarkers;
//        }
//
//    public boolean checkIfUserNamePresent (String username) {
//        return userRepository.findByUsername(username).isPresent();
//    }
//
//    public UserProfileDTO getUserProfileByName(String userName) {
//        Optional<UserAccount> optionalUser = userRepository.findByUsername(userName);
//        Optional<Address> optionalAddress = addressService.findById(optionalUser.get().getAddress().getId());
//        return new UserProfileDTO(optionalUser.get().getId(),optionalUser.get().getUsername(),optionalAddress.get().getStreet(),
//                optionalAddress.get().getStreetNumber(),optionalAddress.get().getPostalcode(),optionalAddress.get().getCity(),
//                entryRepository.findEntriesByUser_IdAndType(optionalUser.get().getId(), "offer").size(),
//                entryRepository.findEntriesByUser_IdAndType(optionalUser.get().getId(), "request").size());
//    }
}

