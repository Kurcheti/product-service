package com.ecom.product.service;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.product.entity.Product;
import com.ecom.product.repository.ProductRepo;
import com.ecom.product.util.FileUtils;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	public Product addProduct(Product product, MultipartFile multipartFile) throws IOException {
		 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 String fileCode = FileUtils.saveFile(fileName, multipartFile,"product_img");
		 product.setProductImage(fileCode+"-"+fileName);
		return productRepo.save(product);
	}
	
	public Product updateProduct(Product product, MultipartFile multipartFile) throws IOException {
		productRepo.findById(product.getProductId()).orElseThrow(
				()->new RuntimeException("Product not found with "+product.getProductId()));
		 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 String fileCode = FileUtils.saveFile(fileName, multipartFile,"product_img");
		 product.setProductImage(fileCode+"-"+fileName);
		return productRepo.save(product);
	}
	
	public Product getProductById(Integer productId) {
		return productRepo.findById(productId).get();
	}
	
	public Page<Product> getCategories(Product product,Pageable pageable){
		return null;
	}
	
	public List<Product> getProducts(){
		return productRepo.findAll();
	}
	
	public void deleteProductById(Integer productId) {
		productRepo.deleteById(productId);
	}

}
