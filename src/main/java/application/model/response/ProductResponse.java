package application.model.response;

import application.model.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int totalPage;
    private int page;
    private List<ProductDTO> listProduct = new ArrayList<>();
}
