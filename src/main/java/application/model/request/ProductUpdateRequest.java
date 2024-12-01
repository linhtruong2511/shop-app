package application.model.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateRequest {
    private String name;
    private String code;
    private Double price;
    @Min(value = 0, message = "product quantity is not less than 0")
    private Integer stockQuantity;
    private String description;
}
