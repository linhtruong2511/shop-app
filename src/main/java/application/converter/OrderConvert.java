package application.converter;

import application.common.OrderEnum;
import application.model.request.OrderSearchRequest;
import application.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderConvert {
    public OrderSearchRequest paramsToSearchRequest(Map<String, String> params){
        return OrderSearchRequest.builder()
                .customerID(MapUtil.toObj("customerID", Long.class, params))
                .status(OrderEnum.getStatusByValue(MapUtil.toObj("status", String.class, params)))
                .nameProduct(MapUtil.toObj("nameProduct", String.class, params))
                .build();
    }
}
