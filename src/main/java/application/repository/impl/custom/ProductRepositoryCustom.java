package application.repository.impl.custom;

import application.entity.Product;
import application.model.dto.ProductDTO;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> getProducts(String name, String userName, String userAddress);
}
