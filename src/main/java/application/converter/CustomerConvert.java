package application.converter;

import application.builder.CustomerSearchCriteria;
import application.util.MapUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerConvert {
    public static CustomerSearchCriteria paramsToSearchCriteria(Map<String, String> params) {
     CustomerSearchCriteria criteria = new CustomerSearchCriteria.Builder()
                                            .setName(MapUtil.toObj("name", String.class, params))
                                            .setAddress(MapUtil.toObj("address", String.class, params))
                                            .setPhoneNumber(MapUtil.toObj("phoneNumber", String.class, params))
                                            .build();
        return criteria;
    }
}
