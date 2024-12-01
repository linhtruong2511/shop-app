package application.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IntrospectRequest {
    private String token;
}
