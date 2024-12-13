package application.model.request;

import application.entity.User;
import application.model.dto.UserI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SignUpUserRequest implements UserI {
    private String email;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    private String lastName;
    private String firstName;
    private LocalDate dod;
}
