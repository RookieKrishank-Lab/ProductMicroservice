package com.productMicroService.Product.repository;

import com.productMicroService.Product.model.ProductDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductDetails, Integer> {
}
