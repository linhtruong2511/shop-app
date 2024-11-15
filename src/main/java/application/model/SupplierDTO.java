package application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SupplierDTO {
    private  Long Id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

}
