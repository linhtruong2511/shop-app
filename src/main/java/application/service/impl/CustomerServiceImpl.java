package application.service.impl;

import application.builder.CustomerSearchCriteria;
import application.converter.CustomerConvert;
import application.entity.Customer;
import application.entity.Supplier;
import application.exception.CustomerNotFoundException;
import application.model.CustomerDTO;
import application.model.SupplierDTO;
import application.model.response.PageResponse;
import application.repository.CustomerRepository;
import application.service.CustomerService;
import application.utils.MapUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public List<CustomerDTO> findCustomers(Map<String, String> params) {
        CustomerSearchCriteria criteria = CustomerConvert.paramsToSearchCriteria(params);
        List<Customer> customers = customerRepository.findByNameContainingAndAddressContainingAndPhoneNumberContaining(
                criteria.getName(),
                criteria.getAddress(),
                criteria.getPhoneNumber()
        );
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        CustomerDTO customerDTOMapping = new CustomerDTO();
        for (Customer c : customers) {
            customerDTOMapping = mapper.map(c, CustomerDTO.class);
            customerDTOS.add(customerDTOMapping);
        }
        return customerDTOS;
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            return new CustomerNotFoundException("customer not found");
        });
        CustomerDTO customerDTO = mapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }

    @Override
    public PageResponse<CustomerDTO> getSuppliersWithPage(Pageable pageable) {
        Page<Customer> products = customerRepository.findAll(pageable);
        List<CustomerDTO> supplierDTOS = new ArrayList<>();
        products.forEach(item -> {
            CustomerDTO customerDTO = mapper.map(item, CustomerDTO.class);
            supplierDTOS.add(customerDTO);
        });
        return PageResponse.<CustomerDTO>builder()
                .currentPage(pageable.getPageNumber())
                .totalElement(products.getTotalElements())
                .totalPage(products.getTotalPages())
                .data(supplierDTOS)
                .build();
    }
}
