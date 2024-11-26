package application.model.request;

import application.common.OrderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSearchRequest {
    private OrderEnum status;
    private Long customerID;
    private String nameProduct;
}
