package com.furkantkgz.renartbackend.service;

import com.furkantkgz.renartbackend.model.ProductDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto getProductById(long id);
}
