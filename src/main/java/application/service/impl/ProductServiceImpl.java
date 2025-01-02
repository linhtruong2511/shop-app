package application.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import application.builder.ProductSearchCriteria;
import application.converter.ProductConvert;
import application.entity.Image;
import application.exception.ProductNotFoundException;
import application.exception.SupplierNotFoundException;
import application.model.request.ProductCreationRequest;
import application.model.request.ProductUpdateRequest;
import application.model.response.PageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import application.entity.Product;
import application.model.dto.ProductDTO;
import application.repository.ProductRepository;
import application.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
    ProductRepository productRepository;
	@Autowired
	ModelMapper mapper;
	@Override
	public List<ProductDTO> getProducts(Map<String, String> params) {
		String name = params.get("name");
		String userName = params.get("userName");
		String userAddress = params.get("userAddress");

		List<Product> products =  productRepository.getProducts(
				name,
				userName,
				userAddress);
		List<ProductDTO> productDTOS = new ArrayList<>();
		for(Product product : products){
			if(product.isEnabled()){
				productDTOS.add(mapper.map(product, ProductDTO.class));
			}
		}
		return productDTOS;
	}

	@Override
	public ProductDTO getProduct(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("PRODUCT NOT FOUND"));
		if(product.isEnabled()){
			return mapper.map(product, ProductDTO.class);
		}
		else {
			throw new ProductNotFoundException("PRODUCT NOT FOUND");
		}
	}

	@Override
	public ProductDTO createProduct(ProductCreationRequest request) {
		Product product = mapper.map(request, Product.class);
		for(String url : request.getImageUrls()){
			Image image = new Image();
			image.setUrl(url);
			image.setProduct(product);
			product.getImages().add(image);
		}
		product = productRepository.save(product);
		ProductDTO productDTO = mapper.map(product, ProductDTO.class);
		return productDTO;
	}

	@Override
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("PRODUCT NOT EXISTS"));
		productRepository.delete(product);
	}

	@Override
	public ProductDTO updateProduct(ProductUpdateRequest request, Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("PRODUCT NOT FOUND"));
		mappingProduct(product, request);
		productRepository.save(product);
		return mapper.map(productRepository.save(product), ProductDTO.class);
	}

	@Override
	public PageResponse<ProductDTO> getProductsWithPage(Pageable pageable) {
		Page<Product> page = productRepository.findAll(pageable);
		List<ProductDTO> result = new ArrayList<>();
		page.forEach(
				item -> {
					if(item.isEnabled())
						result.add(mapper.map(item, ProductDTO.class));
				}
		);
		return PageResponse.<ProductDTO>builder()
				.totalPage(page.getTotalPages())
				.totalElement(page.getTotalElements())
				.pageSize(page.getSize())
				.data(result)
				.build();
	}
	private void mappingProduct(Product product, ProductUpdateRequest request){
		if(request.getName() != null) product.setName(request.getName());
		if(request.getCode() != null) product.setCode(request.getCode());
		if(request.getPrice() != null) product.setPrice(request.getPrice());
		if(request.getDescription() != null) product.setDescription(request.getDescription());
		if(request.getStockQuantity() != null) product.setStockQuantity(request.getStockQuantity());
	}
}
