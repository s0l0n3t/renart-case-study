package com.furkantkgz.renartbackend.utils;

import com.furkantkgz.renartbackend.config.GoldPriceConfig;
import com.furkantkgz.renartbackend.config.PopularityStarConfig;
import com.furkantkgz.renartbackend.model.ProductDto;
import com.furkantkgz.renartbackend.model.ProductEntity;

public class ProductMapper {

    public static ProductDto toDto(ProductEntity productEntity,GoldPriceConfig goldPriceConfig) {
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .weight(productEntity.getWeight())
                .popularity_score(PopularityStarConfig.popularityToStar(PopularityStarConfig.roundPopularity(productEntity.getPopularity_score())))
                .image_yellow(productEntity.getImage_yellow())
                .image_rose(productEntity.getImage_rose())
                .image_white(productEntity.getImage_white())
                .price(Float.valueOf(GoldPriceConfig.setGoldPrice(goldPriceConfig,productEntity)))
                .build();
    }
    public static ProductEntity toEntity(ProductDto productDto) {
        return ProductEntity.builder()
                .id(productDto.getId())
                .weight(productDto.getWeight())
                .name(productDto.getName())
                .image_white(productDto.getImage_white())
                .image_rose(productDto.getImage_rose())
                .popularity_score(PopularityStarConfig.popularityToStar(PopularityStarConfig.roundPopularity(productDto.getPopularity_score())))
                .image_yellow(productDto.getImage_yellow())
                .build();
    }
}
