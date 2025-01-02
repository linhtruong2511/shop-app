package application.model.request;

import application.entity.Role;
import application.entity.User;
import application.model.dto.UserI;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.support.MessageSourceSupport;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest implements UserI {
    @Size(min = 4, message = "USERNAME_INVALID")
    String username;

    @Email(message = "EMAIL_INVALID")
    @NotNull(message = "EMAIL_IS_NOT_NULL")
    String email;

    @Size(min = 8, message = "PASSWORD_INVALID")
    @NotNull(message = "PASSWORD_NULL")
    @NotBlank(message = "PASSWORD_BLANK")
    String password;

    @NotNull(message = "FIRST_NAME_INVALID")
    @NotBlank(message = "FIRST_NAME_INVALID")
    String firstName;

    @NotNull(message = "LAST_NAME_INVALID")
    @NotBlank(message = "LAST_NAME_INVALID")
    String lastName;

    LocalDate dod;
    String address;
    String phoneNumber;
}
