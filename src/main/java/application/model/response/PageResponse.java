package application.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T>{
    long currentPage;
    long totalPage;
    long pageSize;
    long totalElement;
    List<T> data = Collections.emptyList();
}
