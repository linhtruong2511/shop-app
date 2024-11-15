package application.model.response;

import application.entity.OrderItem;
import application.common.OrderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Double totalAmount;
    private OrderEnum orderEnum;
    private List<OrderItem> orderItems;
}
