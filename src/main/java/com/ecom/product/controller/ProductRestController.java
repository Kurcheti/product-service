package com.ecom.product.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.product.entity.Category;
import com.ecom.product.entity.Product;
import com.ecom.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/products")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(path="/addProduct", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Product> addProduct(@RequestPart("product") String productJson, @RequestPart("file") MultipartFile multipartFile) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(productJson, Product.class);
		return new ResponseEntity<>(productService.addProduct(product, multipartFile),HttpStatus.OK);
	}
	
	
	@PostMapping(path="/updateProduct", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Product> updateProduct(@RequestPart("product") String productJson, @RequestPart("file") MultipartFile multipartFile) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(productJson, Product.class);
		return new ResponseEntity<>(productService.updateProduct(product, multipartFile),HttpStatus.OK);
	}
	
	@PostMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts(){
		return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
	}
	
	@GetMapping(path="/getProductById/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Integer productId){
		return new ResponseEntity<>(productService.getProductById(productId),HttpStatus.OK);
	}
	
	@GetMapping(path="/deleteProductById/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") Integer productId){
		productService.deleteProductById(productId);
		return new ResponseEntity<>("Product "+productId+" deleted Successfully",HttpStatus.OK);
	}
	
	
}
