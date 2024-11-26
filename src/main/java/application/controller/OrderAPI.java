package application.controller;

import application.entity.Orders;
import application.model.dto.OrderDTO;
import application.model.response.APIResponse;
import application.model.response.OrderResponse;
import application.common.OrderEnum;
import application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderAPI {
    @Autowired
    OrderService orderService;

    @PostMapping("/order/{cid}")
    APIResponse<OrderDTO> createOrder(@PathVariable(value = "cid") Long customerID,
                                      @RequestBody Map<Long, Integer> productID){
        APIResponse<OrderDTO> api = new APIResponse<>();
        OrderDTO orderDTO = orderService.createOrder(customerID, productID);
        api.setResult(orderDTO);
        api.setCode("1000");
        return api;
    }

    @GetMapping("/order/{id}")
    APIResponse<OrderResponse> readOrder(@PathVariable(value = "id") Long orderID){
        APIResponse<OrderResponse> api = new APIResponse<>();
        Orders order = orderService.readOrder(orderID);

        OrderResponse orderResponse = OrderResponse.builder()
                .totalAmount(order.getTotalAmount())
                .orderEnum(order.getOrderStatus())
                .orderItems(order.getOrderItems())
                .build();

        api.setResult(orderResponse);
        api.setCode("1000");
        return api;
    }

    @GetMapping(value = "/order/search")
    APIResponse<List<OrderDTO>> searchOrders(@RequestParam Map<String, String> params){
        APIResponse<List<OrderDTO>> api = new APIResponse<>();
        List<OrderDTO> orderDTOS = orderService.searchOrders(params);
        if(orderDTOS.isEmpty()){
            api.setError("order not found");
            api.setCode("1005");
        }
        else{
            api.setCode("1000");
        }
        api.setResult(orderDTOS);
        return api;
    }

    @DeleteMapping("/order/{id}")
    APIResponse<String> deleteOrder(Long id){
        APIResponse<String> api = new APIResponse<>();
        orderService.deleteOrder(id);
        api.setCode("1000");
        return api;
    }

    @PutMapping("/order/{id}")
    APIResponse<String> updateOrder(@PathVariable(value = "id") Long orderID,
                                    @RequestParam(value = "status") String status){
        APIResponse<String> api = new APIResponse<>();
        for(OrderEnum oe : OrderEnum.values()){
            if(oe.toString().equals(status)){
                orderService.updateOrder(oe, orderID);
                api.setCode("1000");
                api.setResult("success");
                return api;
            }
        }
        api.setCode("1005");
        api.setError("status validate");
        return api;
    }

}
