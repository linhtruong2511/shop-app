package application.model.dto;

import application.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    String fullName;
    String username;
    String address;
    String phoneNumber;
    String email;
    List<ProductDTO> products;
    List<OrderDTO> orders;
}
