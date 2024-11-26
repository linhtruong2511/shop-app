package application.repository;

import java.util.List;

import application.builder.ProductSearchCriteria;
import application.entity.Product;
import application.repository.impl.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
	@Query(value = "SELECT p.* " +
			"FROM product p " +
			"LEFT JOIN user_product up ON p.id = up.product_id " +
			"LEFT JOIN user u ON up.user_id = u.id " +
			"WHERE (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%')) " +
			"AND (:userName IS NULL OR CONCAT(COALESCE(u.last_name, ''), COALESCE(u.first_name, '')) LIKE CONCAT('%', :userName, '%')) " +
			"AND (:userAddress IS NULL OR u.address LIKE CONCAT('%', :userAddress, '%')) " +
			"AND p.enabled = 1 " +
			"AND (u.enabled = 1 OR u.id IS NULL)",
			nativeQuery = true)
	List<Product> searchProducts(@Param("name") String name,
								 @Param("userName") String userName,
								 @Param("userAddress") String userAddress);

	List<Product> getProducts(ProductSearchCriteria criteria);
	Product getProductById(Long  id);
	Product createProduct(Product product);
	Product updateProduct(Product product, Long id);
}
