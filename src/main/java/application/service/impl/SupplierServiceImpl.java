package application.service.impl;

import application.entity.Supplier;
import application.exception.SupplierNotFoundException;
import application.model.SupplierDTO;
import application.model.request.SupplierCreationRequest;
import application.model.request.SupplierSearchRequest;
import application.model.request.SupplierUpdateRequest;
import application.model.response.PageResponse;
import application.repository.SupplierRepository;
import application.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepository repository;
    @Autowired
    ModelMapper mapper;
    @Override
    public List<SupplierDTO> getSuppliers(SupplierSearchRequest request){
        List<Supplier> suppliers = repository.findByNameContainingAndAddressContainingAndProductsNameContaining(
                request.getName(),
                request.getAddress(),
                request.getProductName()
        );
        List<SupplierDTO> responseDTO = new ArrayList<>();
        for(Supplier item : suppliers){
            SupplierDTO dto = mapper.map(item, SupplierDTO.class);
            responseDTO.add(dto);
        }
        return responseDTO;
    }
    @Override
    public List<SupplierDTO> getSuppliers2(SupplierSearchRequest request){
        List<Supplier> suppliers = repository.findSuppliers(
                request.getName(),
                request.getAddress(),
                request.getProductName()
        );
        List<SupplierDTO> responseDTO = new ArrayList<>();
        for(Supplier item : suppliers){
            SupplierDTO dto = mapper.map(item, SupplierDTO.class);
            responseDTO.add(dto);
        }
        return responseDTO;
    }
    @Override
    public Supplier createSupplier(SupplierCreationRequest request) {
        Supplier supplier = mapper.map(request, Supplier.class);
        return repository.save(supplier);
    }

    @Override
    public SupplierDTO updateSupplier(Long id, SupplierUpdateRequest request) {
        Supplier supplier = repository.findById(id).orElseThrow(() ->
                new SupplierNotFoundException("supplier not found")
        );
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());
        supplier.setName(request.getName());
        supplier.setPhoneNumber(request.getPhoneNumber());

        Supplier entity = repository.save(supplier);
        SupplierDTO dto = mapper.map(entity, SupplierDTO.class);
        return dto;
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = repository.findById(id).orElseThrow(() -> {
            return new SupplierNotFoundException("Supplier not found");
        });
        repository.delete(supplier);
    }

    @Override
    public SupplierDTO getSupplier(Long id) {
        Supplier supplier = repository.findById(id).orElseThrow(() ->
                new SupplierNotFoundException("supplier not found"));
        SupplierDTO supplierDTO = mapper.map(supplier, SupplierDTO.class);
        return supplierDTO;
    }

    @Override
    public PageResponse<SupplierDTO> getSuppliersWithPage(Pageable pageable) {
        Page<Supplier> products = repository.findAll(pageable);
        List<SupplierDTO> supplierDTOS = new ArrayList<>();
        products.forEach(item -> {
            SupplierDTO supplierDTO = mapper.map(item, SupplierDTO.class);
            supplierDTOS.add(supplierDTO);
        });
        return  PageResponse.<SupplierDTO>builder()
                .currentPage(pageable.getPageNumber())
                .totalElement(products.getTotalElements())
                .totalPage(products.getTotalPages())
                .data(supplierDTOS)
                .build();
    }
}
