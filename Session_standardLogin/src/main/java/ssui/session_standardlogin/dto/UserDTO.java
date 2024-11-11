package ssui.session_standardlogin.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private String id;
    private String password;
    private String role;

    public UserDTO(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public UserDTO() {

    }
}
