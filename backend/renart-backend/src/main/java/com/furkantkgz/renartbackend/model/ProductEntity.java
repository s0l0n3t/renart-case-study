package com.furkantkgz.renartbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Getter
    @Column(name = "popularity_score", nullable = false)
    private float popularity_score;
    @Column(name = "weight",nullable = false)
    private float weight;
    @Column(name = "image_yellow")
    private String image_yellow;
    @Column(name = "image_rose")
    private String image_rose;
    @Column(name = "image_white")
    private String image_white;
}
