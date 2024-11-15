package application.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
