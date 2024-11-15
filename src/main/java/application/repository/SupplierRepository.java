package application.repository;

import application.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByNameContainingAndAddressContainingAndPhoneNumberContaining(String name, String address, String phoneNumber);
    @Query(value = "select s from Supplier s where " +
            "s.name like %:name% " +
            "and s.address like %:address% " +
            "and s.phoneNumber like %:phoneNumber%")
    List<Supplier> findSuppliers(@Param(value = "name") String name,
                                 @Param(value = "address") String address,
                                 @Param(value = "phoneNumber") String phoneNumber);
}
