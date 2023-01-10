package com.productMicroService.Product.service;


import com.productMicroService.Product.model.ProductDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<ProductDetails> findAll();

    ProductDetails save(ProductDetails crop);

    void deleteById(int productId);

    ProductDetails findById(int id);
}
