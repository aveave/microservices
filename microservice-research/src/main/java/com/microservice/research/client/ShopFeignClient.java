package com.microservice.research.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.research.dto.ProductDto;

@FeignClient(name="shop", url = "http://localhost:8090")
public interface ShopFeignClient {

        @GetMapping("/api/products")
        List<ProductDto> getProducts();

        @GetMapping("/api/products/{id}")
        ProductDto getProductById(@PathVariable Long id);

}
