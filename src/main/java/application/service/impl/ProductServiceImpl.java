package application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import application.exception.ProductNotFoundException;
import application.model.request.ProductCreationRequest;
import application.model.request.ProductUpdateRequest;
import application.model.response.PageResponse;
import application.model.response.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import application.builder.ProductSearchCriteria;
import application.converter.ProductConvert;
import application.entity.Product;
import application.model.ProductDTO;
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
		ProductSearchCriteria criteria = ProductConvert.paramsToSearchCriteria(params);
		List<Product> productEntities = productRepository.getProducts(criteria);
		List<ProductDTO> productResponses = new ArrayList<ProductDTO>();
		for(Product item : productEntities) {
			ProductDTO productResponse = mapper.map(item, ProductDTO.class);
			productResponses.add(productResponse);
		}
		return productResponses;
	}

	@Override
	public ProductDTO getProduct(Long id) {
		Product product = productRepository.getProductById(id);
		ProductDTO dto = new ProductDTO();
		// convert value
		dto.setName(product.getName());
		dto.setId(product.getId());
		dto.setCode(product.getCode());
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		dto.setStockQuantity(product.getStockQuantity());
		dto.setDescription(product.getDescription());

        return dto;
	}

	@Override
	public ProductDTO createProduct(ProductCreationRequest request) {
		Product product = mapper.map(request, Product.class);
		Product entity = productRepository.createProduct(product);
		ProductDTO dto = mapper.map(entity, ProductDTO.class);
		return dto;
	}

	@Override
	public void deleteProduct(Long[] id) {
		productRepository.deleteProductById(id);
	}

	@Override
	public ProductDTO updateProduct(ProductUpdateRequest request, Long id) {
		Product product = mapper.map(request, Product.class);
		Product entity = productRepository.updateProduct(product, id);
		ProductDTO dto = mapper.map(entity, ProductDTO.class);
		return dto;
	}

	@Override
	public PageResponse<ProductDTO> getProductsWithPage(Pageable pageable) {
		Page<Product> products = productRepository.findAll(pageable);
		List<ProductDTO> productDTOS = new ArrayList<>();
		products.forEach(item -> {
			ProductDTO productDTO = mapper.map(item, ProductDTO.class);
			productDTOS.add(productDTO);
		});
		return  PageResponse.<ProductDTO>builder()
				.currentPage(pageable.getPageNumber())
				.totalElement(products.getTotalElements())
				.totalPage(products.getTotalPages())
				.data(productDTOS)
				.build();
	}
}
