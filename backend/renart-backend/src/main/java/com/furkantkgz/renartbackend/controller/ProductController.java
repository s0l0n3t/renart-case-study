package com.furkantkgz.renartbackend.controller;


import com.furkantkgz.renartbackend.model.ProductDto;
import com.furkantkgz.renartbackend.service.ProductServiceImpl;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prod")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/status")
    public ResponseEntity<HttpStatus> controllerStatus(){
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }

    @GetMapping("/productlist")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(value = "/find",params = "id")
    public ResponseEntity findProductById(@RequestParam long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping(value = "/pricerange")
    public ResponseEntity filterPriceRange(@RequestParam(name = "min") int min,@RequestParam(name = "max") int max){
        List<ProductDto> products = productService.getAllProducts();
        List<ProductDto> filteredProducts = new ArrayList<>();
        for(ProductDto product : products){
            if(product.getPrice() >= min && product.getPrice() <= max){
                filteredProducts.add(product);
            }
        }
        return ResponseEntity.ok(filteredProducts);
    }
    @GetMapping(value = "/findpopularity",params = "value")
    public ResponseEntity filterPopularityScore(float value){
        List<ProductDto> products = productService.getAllProducts();
        List<ProductDto> filteredProducts = new ArrayList<>();
        for(ProductDto product : products){
            if(product.getPopularity_score() >= value){
                filteredProducts.add(product);
            }
        }
        return ResponseEntity.ok(filteredProducts);
    }
}
