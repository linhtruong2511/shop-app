package application.common;

import org.aspectj.weaver.ast.Or;

public enum OrderEnum {
    CHUA_XU_LY("CHUA_XU_LY"),
    DANG_XU_LY("DANG_XU_LY"),
    DA_GIAO("DA_GIAO"),
    DA_HUY("DA-HUY");
    final String value;
    OrderEnum(String value){
        this.value = value;
    }
    public static OrderEnum getStatusByValue(String value){
        for(OrderEnum oe : OrderEnum.values()){
            if(oe.value.equals(value)){
                return oe;
            }
        }
        return null;
    }
}
