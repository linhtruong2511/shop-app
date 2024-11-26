package application.service.impl;

import application.converter.OrderConvert;
import application.entity.Orders;
import application.exception.OrderNotFoundException;
import application.model.dto.OrderDTO;
import application.model.request.OrderSearchRequest;
import application.repository.OrderRepository;
import application.repository.ProductRepository;
import application.common.OrderEnum;
import application.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    ProductRepository productRepository;
    OrderConvert orderConvert;
    ModelMapper mapper;

    @Override
    @Transactional
    public OrderDTO createOrder(Long customerId, Map<Long, Integer> productIdaAndQuantity) {
        return null;
    }

    @Override
    @Transactional
    public void updateOrder(OrderEnum status, Long orderId) {
        Orders orders = orderRepository.findById(orderId).
                orElseThrow(() -> new OrderNotFoundException("order not found"));
        orders.setOrderStatus(status);
        orderRepository.save(orders);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        Orders order = orderRepository.findById(id).orElseThrow(
                () -> {
                    return new OrderNotFoundException("order not found");
                }
        );
        orderRepository.delete(order);
    }

    @Override
    public Orders readOrder(Long id) {
        Orders orders = orderRepository.findById(id).orElseThrow(
                () -> {
                    return new OrderNotFoundException("order not found");
                }
        );
        return orders;
    }

    @Override
    public List<OrderDTO> searchOrders(Map<String, String> params) {
        OrderSearchRequest orderSearchRequest = orderConvert.paramsToSearchRequest(params);
        List<Orders> orders = orderRepository.searchOrder(orderSearchRequest);
        if(orders.isEmpty()){
            throw new OrderNotFoundException("order not found");
        }
        return orders.stream().map(item -> mapper.map(item, OrderDTO.class))
                .collect(Collectors.toList());
    }
}
