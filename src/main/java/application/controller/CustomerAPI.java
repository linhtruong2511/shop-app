package application.controller;

import application.model.CustomerDTO;
import application.model.SupplierDTO;
import application.model.response.APIResponse;
import application.model.response.PageResponse;
import application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerAPI {
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/customer/{id}")
    APIResponse<CustomerDTO> getCustomer(@PathVariable  Long id){
        CustomerDTO customerDTO = customerService.getCustomer(id);
        APIResponse<CustomerDTO> api = new APIResponse();
        api.setResult(customerDTO);
        api.setCode("1000");
        return api;
    }

    @GetMapping(value = "/customers/")
    APIResponse<List<CustomerDTO>> findCustomers(@RequestParam Map<String, String> params){
        List<CustomerDTO> customerDTOS = customerService.findCustomers(params);
        APIResponse<List<CustomerDTO>> api = new APIResponse<>();
        if(customerDTOS.isEmpty()) {
            api.setCode("1002");
            api.setError("Not found");
        }
        else api.setCode("1000");
        api.setResult(customerDTOS);
        return api;
    }

    @GetMapping("/customer/")
    public APIResponse<PageResponse<CustomerDTO>> getSuppliersWithPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                       @RequestParam(name = "size", defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        PageResponse<CustomerDTO> pageResponse = customerService.getSuppliersWithPage(pageable);
        APIResponse<PageResponse<CustomerDTO>> api = new APIResponse<>();
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
