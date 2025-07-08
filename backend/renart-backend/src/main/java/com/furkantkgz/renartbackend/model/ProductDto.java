package com.furkantkgz.renartbackend.model;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private long id;
    private String name;
    private float popularity_score;
    private float weight;
    private String image_yellow;
    private String image_rose;
    private String image_white;
    private float price;
}
