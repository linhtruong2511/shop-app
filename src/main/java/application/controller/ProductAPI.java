package application.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import application.model.request.ProductCreationRequest;
import application.model.request.ProductUpdateRequest;
import application.model.response.APIResponse;
import application.model.response.PageResponse;
import application.model.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.model.ProductDTO;
import application.service.ProductService;

@RestController
@Validated
public class ProductAPI {
    @Autowired
    ProductService service;

    @GetMapping(value = "/product/search")
    public APIResponse<List<ProductDTO>> getProducts(@RequestParam Map<String, String> params) {
        List<ProductDTO> productDTOS = service.getProducts(params);
        APIResponse<List<ProductDTO>> api = new APIResponse<>();
        if(!productDTOS.isEmpty()){
            api.setCode("1000");
            api.setResult(productDTOS);
        }
        else{
            api.setCode("1001");
            api.setError("not found product");
        }
        return api;
    }

    @GetMapping(value = "/product/")
    public APIResponse<PageResponse<ProductDTO>> getProductsWithPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        PageResponse<ProductDTO> pageResponse = service.getProductsWithPage(pageable);
        APIResponse<PageResponse<ProductDTO>> api = new APIResponse<>();
        if(pageResponse.getData().isEmpty()){
            api.setError("product not found");
            api.setCode("1005");
        }
        else{
            api.setResult(pageResponse);
            api.setCode("1000");
        }
        return api;
    }

    @GetMapping(value = "/product/{id}")
    public APIResponse<ProductDTO> getProduct(@PathVariable(value = "id") Long id) {
        ProductDTO productResponse = service.getProduct(id);
        APIResponse<ProductDTO> api = new APIResponse<>();
        api.setCode("1000");
        api.setResult(productResponse);
        return api;
    }

    @PostMapping(value = "/product/")
    public APIResponse<ProductDTO> createProduct(@RequestBody @Valid ProductCreationRequest request) {
        ProductDTO productResponse = service.createProduct(request);
        APIResponse<ProductDTO> api = new APIResponse<>();
        api.setCode("1000");
        api.setResult(productResponse);
        return api;
    }

    @PutMapping(value = "/product/{id}")
    public APIResponse<ProductDTO> updateProduct(@RequestBody @Valid ProductUpdateRequest request,
                                                 @PathVariable Long id) {
        ProductDTO productResponse = service.updateProduct(request, id);
        APIResponse<ProductDTO> api = new APIResponse<>();
        api.setCode("1000");
        api.setResult(productResponse);
        return api;
    }

    @DeleteMapping(value = "/product/{id}")
    public APIResponse<String> deleteProduct(@PathVariable("id") Long[] id) {
        service.deleteProduct(id);
        APIResponse<String> api = new APIResponse<>();
        api.setCode("1000");
        api.setResult("delete success");
        return api;
    }

}
