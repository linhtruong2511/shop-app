package application.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
    private String code = "1000";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;
}
