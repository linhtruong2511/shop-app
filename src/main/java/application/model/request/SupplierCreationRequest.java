package application.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierCreationRequest {
    @NotBlank(message = "name is not blank")
    @NotNull(message = "name is not null")
    private String name;
    @NotNull(message = "address is not null")
    @NotBlank(message = "address is not blank")
    private String address;
    @Size(min = 10, max = 12, message = "phone number is not valid")
    @NotNull(message = "phone number is null")
    private String phoneNumber;
    @Email(message = "email is not valid")
    @NotNull(message = "email is null")
    private String email;
    private List<Long> productIDs;
}
