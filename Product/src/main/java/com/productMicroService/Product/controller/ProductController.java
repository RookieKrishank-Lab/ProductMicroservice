package com.productMicroService.Product.controller;


import com.productMicroService.Product.model.ProductDetails;
import com.productMicroService.Product.repository.ProductRepository;
import com.productMicroService.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/add")
    public ResponseEntity<String> insertCrops(@RequestBody ProductDetails productDetails) {
        try {
            productService.save(productDetails);
            return new ResponseEntity<String>("Product added successfully", HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<String>("There have some problem in the entry", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allProduct")
    public ResponseEntity<Object> getAllCrops(){
        try {
            List<ProductDetails> list=new ArrayList<>();
            productService.findAll().forEach(list::add);
            if(!list.isEmpty()) {
                return new ResponseEntity<Object>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<Object>("There is no product in the list", HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity<Object>("Connection problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<Object> updateProductById(@PathVariable("productId") int productId, @RequestBody ProductDetails productDetails)
    {
        try {
            Optional<ProductDetails> op=productRepository.findById(productId);
            if(op.isPresent())
            {
                ProductDetails productDetails1=op.get();
                productService.save(productDetails);
                return new ResponseEntity<Object>("The Data is updated successfully for id "+productDetails1.getProductId(), HttpStatus.OK);

            }
            else
            {
                return new ResponseEntity<Object>("ProductID NOT FOUND", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e) {
            return new ResponseEntity<Object>("Connection problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable("productId") int productId) {
        try {
            Optional<ProductDetails> op = productRepository.findById(productId);
            if(op.isPresent()) {
                productService.deleteById(productId);
                return new ResponseEntity<String>("Product deleted Successfully", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("There is no such Product Id", HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity<String>("Connection problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
