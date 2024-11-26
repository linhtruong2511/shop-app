package application.repository;

import application.entity.Orders;
import application.entity.Role;
import application.repository.impl.custom.OrderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>, OrderRepositoryCustom {
}
