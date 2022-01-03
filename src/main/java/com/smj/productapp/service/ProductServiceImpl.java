package com.smj.productapp.service;

import com.smj.productapp.dao.ProductDao;
import com.smj.productapp.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDao dao;

    @Override
    public Mono<Products> saveProduct(Products product) {
        return dao.save(product);
    }

    @Override
    public Mono<Products> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Flux<Products> getAllProducts() {
        return dao.findAll();
    }

    @Override
    public Mono<Products> updateProduct(Products product) {
        return dao.save(product);
    }

    @Override
    public Mono<Void> deleteProduct(Long id) {
        System.out.println("id"+id);
        return dao.deleteById(id);
    }
}
