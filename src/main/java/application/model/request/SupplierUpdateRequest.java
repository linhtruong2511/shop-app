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
public class SupplierUpdateRequest {
    @NotBlank(message = "name is not blank")
    private String name;
    @NotBlank(message = "address is not blank")
    private String address;
    @Size(min = 10, max = 12, message = "phone number is not valid")
    private String phoneNumber;
    @Email(message = "email is not valid")
    private String email;
    private List<Long> productIDs;
}
