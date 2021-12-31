package com.smj.productapp.service;

import com.smj.productapp.model.Products;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Products> saveProduct(Products product);

    Mono<Products> findById(Long id);

    Flux<Products> getAllProducts();

    Mono<Products> updateProduct(Products product);

    Mono<Void> deleteProduct(Long id);
}
