package com.furkantkgz.renartbackend.repository;

import com.furkantkgz.renartbackend.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findById(Long id);
}
