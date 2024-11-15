package application.converter;

import java.util.Map;

import application.builder.ProductSearchCriteria;
import application.utils.MapUtil;
import org.springframework.stereotype.Component;

@Component
public class ProductConvert {
	public static ProductSearchCriteria paramsToSearchCriteria(
			Map<String, String> params) {
		ProductSearchCriteria criteria = new ProductSearchCriteria.Builder()
											.setName(MapUtil.toObj("name", String.class, params))
											.setCode(MapUtil.toObj("code", String.class, params))
											.setMinPrice(MapUtil.toObj("minPrice", Double.class, params))
											.setMaxPrice(MapUtil.toObj("maxPrice", Double.class, params))
											.build();
		return criteria;
	}
}
