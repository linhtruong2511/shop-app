package application.service;

import java.util.List;
import java.util.Map;

import application.model.ProductDTO;
import application.model.request.ProductCreationRequest;
import application.model.request.ProductUpdateRequest;
import application.model.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface ProductService {
	List<ProductDTO> getProducts(Map<String, String> params);
	ProductDTO getProduct(Long id);
	ProductDTO createProduct(ProductCreationRequest request);
	void deleteProduct(Long[] id);
	ProductDTO updateProduct(ProductUpdateRequest request, Long id);
    PageResponse<ProductDTO> getProductsWithPage(Pageable pageable);
}
