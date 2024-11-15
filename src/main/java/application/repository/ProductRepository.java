package application.repository;

import java.util.List;

import application.builder.ProductSearchCriteria;
import application.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> getProducts(ProductSearchCriteria criteria);
	Product getProductById(Long  id);
	Product createProduct(Product product);
	void deleteProductById(Long[] id);
	Product updateProduct(Product product, Long id);
}
