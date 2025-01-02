package application.util;

import java.util.UUID;

public class RandomCode {
    public static String getCode(){
        return UUID.randomUUID().toString().substring(0,15);
    }
}
