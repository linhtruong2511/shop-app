package application.repository.impl.custom;


import application.common.OrderEnum;
import application.entity.Orders;
import application.model.request.OrderSearchRequest;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Orders> searchOrder(OrderSearchRequest request);
}
