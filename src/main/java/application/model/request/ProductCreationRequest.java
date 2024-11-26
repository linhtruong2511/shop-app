package application.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
