package com.smj.productapp.controlller;

import com.smj.productapp.model.Products;
import com.smj.productapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Products> saveProduct(@RequestBody Products product){
      return service.saveProduct(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Products>> findById(@PathVariable("id") Long id){
        Mono<Products> product= service.findById(id);
        return new ResponseEntity<Mono<Products>>(product,product!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public Flux<Products> getAllProducts(){
        return service.getAllProducts();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Products> updateProduct(@RequestBody Products product){
        return service.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(Long id){
        return service.deleteProduct(id);
    }
}
