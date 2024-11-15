package application.service.impl;

import application.converter.OrderConvert;
import application.entity.Customer;
import application.entity.OrderItem;
import application.entity.Orders;
import application.entity.Product;
import application.exception.OrderNotFoundException;
import application.exception.ProductOutOfStock;
import application.model.OrderDTO;
import application.model.request.OrderSearchRequest;
import application.repository.CustomerRepository;
import application.repository.OrderRepository;
import application.repository.ProductRepository;
import application.common.OrderEnum;
import application.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderConvert orderConvert;
    @Autowired
    ModelMapper mapper;
    @Override
    @Transactional
    public OrderDTO createOrder(Long customerId, Map<Long, Integer> productIdaAndQuantity) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> {
            return new RuntimeException("orders not found");
        });
        Orders orders = new Orders();


        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;
        for(Map.Entry<Long, Integer> item : productIdaAndQuantity.entrySet()){
            Product product = productRepository.getProductById(item.getKey());

            if(product.getStockQuantity() < item.getValue())
                throw new ProductOutOfStock("product out of stock");
            else{
                product.setStockQuantity(product.getStockQuantity() - item.getValue());
            }

            OrderItem orderItem = new OrderItem();

            orderItem.setQuantity(item.getValue());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrders(orders);
            orderItem.setProduct(product);
            orderItems.add(orderItem);

            totalAmount += item.getValue() * product.getPrice();
        }

        orders.setOrderItems(orderItems);
        orders.setTotalAmount(totalAmount);
        orders.setOrderStatus(OrderEnum.CHUA_XU_LY);
        orders.setDate(LocalDateTime.now());

        orders.setCustomer(customer);
        orderRepository.save(orders);

        OrderDTO orderDTO = mapper.map(orders, OrderDTO.class);

        return orderDTO;

    }

    @Override
    @Transactional
    public void updateOrder(OrderEnum status, Long orderId) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(
                () -> {return new OrderNotFoundException("order not found");}
        );
        orders.setOrderStatus(status);
        orderRepository.save(orders);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        Orders order = orderRepository.findById(id).orElseThrow(
                () -> {return new OrderNotFoundException("order not found");}
        );
        orderRepository.delete(order);
    }

    @Override
    public Orders readOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> { return new OrderNotFoundException("order not found"); }
        );
    }

    @Override
    public List<OrderDTO> searchOrders(Map<String, String> params) {
        OrderSearchRequest orderSearchRequest = orderConvert.paramsToSearchRequest(params);

            List<Orders> orders = orderRepository.searchOrder(orderSearchRequest);

        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(item -> {
            OrderDTO orderDTO = mapper.map(item, OrderDTO.class);
            orderDTOS.add(orderDTO);
        });
        return orderDTOS;
    }
}
