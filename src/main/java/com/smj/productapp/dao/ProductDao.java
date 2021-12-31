package com.smj.productapp.dao;

import com.smj.productapp.model.Products;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends ReactiveMongoRepository<Products,Long> {
}
