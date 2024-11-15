package application.service;

import application.entity.Supplier;
import application.model.ProductDTO;
import application.model.SupplierDTO;
import application.model.request.SupplierCreationRequest;
import application.model.request.SupplierUpdateRequest;
import application.model.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplierService {
    List<SupplierDTO> getSuppliers(SupplierDTO supplierDTO);

    List<SupplierDTO> getSuppliers2(SupplierDTO supplierDTO);

    Supplier createSupplier(SupplierCreationRequest request);

    SupplierDTO updateSupplier(Long id, SupplierUpdateRequest request);

    void deleteSupplier(Long id);

    SupplierDTO getSupplier(Long id);

    PageResponse<SupplierDTO> getSuppliersWithPage(Pageable pageable);

}
