package application.repository.impl.custom.impl;

import application.entity.Orders;
import application.model.request.OrderSearchRequest;
import application.repository.impl.custom.OrderRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    @Autowired
    EntityManager em;
    @Override
    public List<Orders> searchOrder(OrderSearchRequest request) {
        String sql = createQuery(request);
        Query query = em.createNativeQuery(sql, Orders.class);
        System.out.println(sql);
        List<Orders> orders = query.getResultList();
        return orders;
    }
    private String createQuery(OrderSearchRequest request){
        StringBuilder sql = new StringBuilder("select o.* from orders o ");
        if (request.getNameProduct() != null){
            sql.append("join order_item oi on oi.order_id = o.id " +
                    "join product p on p.id = oi.product_id ");
        }
        sql.append("where 1 = 1 ");
        if(request.getNameProduct() != null){
            sql.append("and p.name like '%" + request.getNameProduct() + "%' ");
        }
        if(request.getCustomerID() != null){
            sql.append("and o.customer_id = " + request.getCustomerID() + " ");
        }
        if(request.getStatus() != null){
            sql.append("and o.order_status = '" + request.getStatus() + "' ");
        }
        return sql.toString();
    }
}
