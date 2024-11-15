package application.utils;

import java.util.Map;

public class MapUtil {
	public static <T> T toObj(String key, Class<T> tClass, Map<String, String> params){
		Object obj = params.getOrDefault(key, null);
		if(obj != null) {
			try {
				if(tClass.getTypeName().equals("java.lang.Integer")) {
					obj = Integer.valueOf(params.get(key));
				}
				else if(tClass.getTypeName().equals("java.lang.Long")) {
					obj = Long.valueOf(params.get(key));
				}
				else if(tClass.getTypeName().equals("java.lang.Double")) {
					obj = Double.valueOf(params.get(key));
				}
				else if(tClass.getTypeName().equals("java.lang.Float")) {
					obj = Float.valueOf(params.get(key));
				}
				else if(tClass.getTypeName().equals("java.lang.String")) {
					obj = params.get(key);
				}
				return tClass.cast(obj);

			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println("casting null");
			}
		}
		return null;
	}
}
