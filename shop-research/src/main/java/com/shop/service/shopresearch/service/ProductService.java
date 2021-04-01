package com.shop.service.shopresearch.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.service.shopresearch.model.Product;

@Service
public class ProductService {

    public List<Product> getProducts() {
        Product product1 = new Product();
        product1.setName("First product");
        product1.setPrice(30);
        Product product2 = new Product();
        product2.setName("Second product");
        product2.setPrice(20);
        return Arrays.asList(product1, product2);
    }
}
