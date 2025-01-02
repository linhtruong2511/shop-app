package application.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequest {
    @NotNull(message = "USER NAME IS NOT NULL")
    @NotBlank(message = "USERNAME IS NOT BLANK")
    private String username;
    @NotNull(message = "PASSWORD IS NOT NUL")
    @NotBlank(message = "PASSWORD IS NOT BLANK")
    private String password;
}
