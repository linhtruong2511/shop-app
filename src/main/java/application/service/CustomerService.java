package application.service;

import application.entity.Customer;
import application.model.CustomerDTO;
import application.model.SupplierDTO;
import application.model.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface CustomerService {
    List<CustomerDTO> findCustomers(Map<String, String> params);
    CustomerDTO getCustomer(Long id);
    PageResponse<CustomerDTO> getSuppliersWithPage(Pageable pageable);
}
