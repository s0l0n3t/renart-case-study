package com.furkantkgz.renartbackend.service;

import com.furkantkgz.renartbackend.config.GoldPriceConfig;
import com.furkantkgz.renartbackend.model.ProductDto;
import com.furkantkgz.renartbackend.model.ProductEntity;
import com.furkantkgz.renartbackend.repository.ProductRepository;
import com.furkantkgz.renartbackend.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private final GoldPriceConfig goldPriceConfig;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,GoldPriceConfig goldPriceConfig) {
        this.productRepository = productRepository;
        this.goldPriceConfig = goldPriceConfig;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findAll()) {
            productDtos.add(ProductMapper.toDto(productEntity, goldPriceConfig));
        }
        return productDtos;
    }

    @Override
    public ProductDto getProductById(long id) {
        return ProductMapper.toDto(productRepository.findById(id).get(), goldPriceConfig);
    }

    public List<ProductDto> getProductByName(String name) {
        return List.of();
    }
}
