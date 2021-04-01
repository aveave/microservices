package com.microservice.research.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.research.client.ShopFeignClient;
import com.microservice.research.configuration.BaseConfig;
import com.microservice.research.configuration.CustomConfig;
import com.microservice.research.dto.ProductDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final ShopFeignClient shopRestClient;
//    private final BaseConfig baseConfig;
    private final CustomConfig customConfig;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return shopRestClient.getProducts();
    }

//    @GetMapping("/property")
//    public String getPropertyValue() {
//        return baseConfig.getTestProperty();
//    }

    @GetMapping("/test")
    public String getCustomName() {
        return customConfig.getTest();
    }
}
