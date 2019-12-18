package de.fasterfood.fasterfood.user;

import com.sun.istack.NotNull;
import javax.validation.constraints.NotEmpty;


public class UserDTO {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String matchingPassword) {
        this.username = username;
        this.password = password;

        this.matchingPassword = matchingPassword;
    }

    public UserDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }
}
