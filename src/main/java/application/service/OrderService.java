package application.service;

import application.common.OrderEnum;
import application.entity.Orders;
import application.model.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderDTO createOrder(Long customerId, Map<Long, Integer> productId);
    void updateOrder(OrderEnum status, Long orderId);
    void deleteOrder(Long id);
    Orders readOrder(Long id);
    List<OrderDTO> searchOrders(Map<String, String> params);
}
