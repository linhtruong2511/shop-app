package application.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import application.builder.ProductSearchCriteria;
import application.entity.Product;
import application.exception.ProductNotFoundException;
import application.repository.ProductRepository;


@Repository
public class ProductRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ModelMapper mapper;

    public List<Product> getProducts(ProductSearchCriteria criteria) {
        StringBuilder hql = createHQL(criteria);
        Query query = entityManager.createQuery(hql.toString(), Product.class);
        setParameter(query, criteria);
        List<Product> productEntities = query.getResultList();
        return productEntities;
    }

    public Product getProductById(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            throw new ProductNotFoundException("product not found");
        }
        return product;
    }

    @Transactional
    public Product createProduct(Product product) {
        entityManager.persist(product);
        return product;
    }

    @Transactional
    public void deleteProductById(Long[] id) {
        for (Long i : id) {
            Product product = entityManager.find(Product.class, i);
            if (product == null) {
                throw new ProductNotFoundException("product not found");
            }
            entityManager.remove(product);
        }
    }

    @Transactional
    public Product updateProduct(Product product, Long id) {
        Product productEntity = entityManager.find(Product.class, id);
        if (productEntity == null) {
            throw new ProductNotFoundException("product not found");
        }

        productEntity.setId(id);
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setStockQuantity(product.getStockQuantity());
        productEntity.setCode(product.getCode());
        productEntity.setDescription(product.getDescription());

        return entityManager.merge(productEntity);
    }

    private StringBuilder createHQL(ProductSearchCriteria criteria) {
        StringBuilder hql = new StringBuilder(" from Product as p where 1 = 1");
        if (criteria.getName() != null) {
            hql.append(" and p.name like :name");
        }
        if (criteria.getCode() != null) {
            hql.append(" and p.code like :code");
        }
        if (criteria.getMinPrice() != null) {
            hql.append(" and p.price >= :minPrice");
        }
        if (criteria.getMaxPrice() != null) {
            hql.append(" and p.price <= :maxPrice");
        }
        return hql;
    }

    private void setParameter(Query query, ProductSearchCriteria criteria) {
        if (criteria.getName() != null) {
            query.setParameter("name", "%" + criteria.getName() + "%");
        }
        if (criteria.getCode() != null) {
            query.setParameter("code", "%" + criteria.getCode() + "%");
        }
        if (criteria.getMinPrice() != null) {
            query.setParameter("minPrice", criteria.getMinPrice());
        }
        if (criteria.getMaxPrice() != null) {
            query.setParameter("maxPrice", criteria.getMaxPrice());
        }
    }

}
