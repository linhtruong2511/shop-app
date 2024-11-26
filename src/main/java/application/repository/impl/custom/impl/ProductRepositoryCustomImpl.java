package application.repository.impl.custom.impl;

import application.entity.Product;
import application.repository.impl.custom.ProductRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    @Autowired
    EntityManager entityManager;
    @Override
    public List<Product> getProducts(String name, String userName, String userAddress) {
        String sql = createQuerySQL(name, userName, userAddress);
        System.out.println("sql: " + sql);
        List<Product> products = entityManager.createNativeQuery(sql, Product.class).getResultList();
        return products;
    }
    private String createQuerySQL(String name, String userName, String userAddress){
        StringBuilder sql = new StringBuilder("select p.* from product p ");
        if(userName != null || userAddress != null){
            sql.append(" join user_product up on up.product_id = p.id " +
                    "join user u on u.id = up.user_id ");
        }
        sql.append(" where 1 = 1 ");
        if(name != null){
            sql.append(" and p.name like '%" + name + "%' ");
        }
        if(userName != null){
            sql.append(" and u.last_name like '%" + userName + "%' ");
        }
        if(userAddress != null){
            sql.append(" and u.address like '%" + userAddress + "%' ");
        }
        return sql.toString();
    }
}
