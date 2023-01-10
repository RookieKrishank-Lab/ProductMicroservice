package com.productMicroService.Product.service;

import com.productMicroService.Product.model.ProductDetails;
import com.productMicroService.Product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImple implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDetails> findAll() {
        List<ProductDetails> list=productRepository.findAll();
        System.out.println(list);
        return list;
    }

    @Override
    public ProductDetails save(ProductDetails productDetails) {
        System.out.println(productDetails);
        return productRepository.save(productDetails);
    }

    @Override
    public void deleteById(int productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDetails findById(int id) {
        Optional<ProductDetails> op = productRepository.findById(id);
        if(op.isPresent()) {
            ProductDetails productDetails=op.get();
            return productDetails;
        }
        else {
            return null;
        }
    }
}
