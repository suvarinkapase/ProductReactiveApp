package com.smj.productapp.controlller;

import com.smj.productapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductController {

    @Autowired
    ProductService service;
}
