package application.model.dto;

import application.common.OrderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String code;
    private LocalDateTime date;
    private OrderEnum orderStatus;
    private Double totalAmount;
}
