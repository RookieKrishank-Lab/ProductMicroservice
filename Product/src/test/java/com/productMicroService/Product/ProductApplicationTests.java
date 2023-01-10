package com.productMicroService.Product;

import com.productMicroService.Product.model.ProductDetails;
import com.productMicroService.Product.repository.ProductRepository;
import com.productMicroService.Product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductApplicationTests {

	@Autowired
	ProductService productService;

	@MockBean
	ProductRepository productRepository;

	@Test
	public void testReadAll() {
		when(productRepository.findAll()).thenReturn(Stream
				.of(new ProductDetails(201,"Jacket",90.23f), new ProductDetails(202,  "Jeans",3434.34f)).collect(Collectors.toList()));
		assertEquals(2, productService.findAll().size());      //assertEquals(Object expected, Object actual)
	}

	@Test
	public void saveProductTest() {
		ProductDetails productDetails=new ProductDetails(201,"Jacket",90.23f);
		when(productRepository.save(productDetails)).thenReturn(productDetails);
		assertEquals(productDetails, productService.save(productDetails));
	}

	@Test
	public void getProductByIdTest() {
		int productId=206;
		ProductDetails productDetails=new ProductDetails(201,"Jacket",90.23f);
		System.out.println("Hello"+ productId);
		when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(productDetails));
		System.out.println(productService.findById(productId)+"and"+productDetails);
		assertEquals(productDetails, productService.findById(productId));
	}
}
