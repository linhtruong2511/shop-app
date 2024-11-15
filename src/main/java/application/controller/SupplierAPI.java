package application.controller;

import application.entity.Supplier;
import application.model.ProductDTO;
import application.model.SupplierDTO;
import application.model.request.SupplierCreationRequest;
import application.model.request.SupplierUpdateRequest;
import application.model.response.APIResponse;
import application.model.response.PageResponse;
import application.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SupplierAPI {
    @Autowired
    SupplierService service;

    @PostMapping(value = "/supplier/")
    APIResponse<Supplier> createSupplier(@RequestBody @Valid SupplierCreationRequest request){
        Supplier supplier = service.createSupplier(request);
        APIResponse<Supplier> api = new APIResponse<>();
        api.setResult(supplier);
        api.setCode("1000");
        return api;
    }

    @PutMapping("/supplier/{id}")
    APIResponse<SupplierDTO> updateSupplier(@RequestBody SupplierUpdateRequest request,
                                            @PathVariable("id") Long id){
        SupplierDTO supplierDTO = service.updateSupplier(id, request);
        APIResponse<SupplierDTO> api = new APIResponse<>();
        api.setResult(supplierDTO);
        api.setCode("1000");
        return api;
    }

    @DeleteMapping("/supplier/{id}")
    APIResponse<String> deleteSupplier(@PathVariable long id){
        service.deleteSupplier(id);
        APIResponse<String> api = new APIResponse<>();
        api.setResult("delete success");
        api.setCode("1000");
        return api;
    }

    @GetMapping("/supplier/{id}")
    APIResponse<SupplierDTO> getSupplier(@PathVariable Long id){
        SupplierDTO supplierDTO = service.getSupplier(id);
        APIResponse<SupplierDTO> api = new APIResponse<>();
        api.setResult(supplierDTO);
        api.setCode("1000");
        return api;
    }

    @GetMapping("/supplier/search")
    APIResponse<List<SupplierDTO>> getAllSupplier(SupplierDTO supplierDTO){
        List<SupplierDTO> productDTOS = service.getSuppliers2(supplierDTO);
        APIResponse<List<SupplierDTO>> api = new APIResponse<>();
        if(productDTOS.isEmpty()){
            api.setCode("1002");
            api.setError("not found");
        }
        else{
            api.setCode("1000");
            api.setResult(productDTOS);
        }
        return api;
    }

    // them phuong thuc lay nha phan phoi theo bang
    @GetMapping("/supplier/")
    public APIResponse<PageResponse<SupplierDTO>> getSuppliersWithPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                     @RequestParam(name = "size", defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        PageResponse<SupplierDTO> pageResponse = service.getSuppliersWithPage(pageable);
        APIResponse<PageResponse<SupplierDTO>> api = new APIResponse<>();
        if(pageResponse.getData().isEmpty()){
            api.setError("supplier not found");
            api.setCode("1005");
        }
        else{
            api.setResult(pageResponse);
            api.setCode("1000");
        }
        return api;
    }
}
