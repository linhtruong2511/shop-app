package application.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NotNull
public class ProductCreationRequest {
    @NotNull(message = "product name is not null")
    private String name;
    @NotNull(message = "product code is not null")
    private String code;
    @NotNull(message = "product price is not null")
    private Double price;
    @Min(value = 0, message = "product quantity is not less than 0")
    @NotNull(message = "product stock quantity is not null")
    private Integer stockQuantity;
    private String description;
    private List<Long> supplierIDs;
}
