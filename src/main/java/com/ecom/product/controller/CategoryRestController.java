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
import com.ecom.product.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;
	@PostMapping(path="/addCategory", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Category> addCategory(@RequestPart("category") String categoryJson, @RequestPart("file") MultipartFile multipartFile) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		Category category = objectMapper.readValue(categoryJson, Category.class);
		return new ResponseEntity<>(categoryService.addCategory(category, multipartFile),HttpStatus.OK);
	}
	
	
	@PostMapping(path="/updateCategory", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Category> updateCategory(@RequestPart("category") String categoryJson, @RequestPart("file") MultipartFile multipartFile) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		Category category = objectMapper.readValue(categoryJson, Category.class);
		return new ResponseEntity<>(categoryService.updateCategory(category, multipartFile),HttpStatus.OK);
	}
	
	@PostMapping("/getAllCategory")
	public ResponseEntity<List<Category>> getAllCategories(){
		return new ResponseEntity<>(categoryService.getCategories(),HttpStatus.OK);
	}
	
	@GetMapping(path="/getCategoryById/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Integer categoryId){
		return new ResponseEntity<>(categoryService.getCategoryById(categoryId),HttpStatus.OK);
	}
	
	@GetMapping(path="/deleteCategoryById/{categoryId}")
	public ResponseEntity<?> deleteCategoryById(@PathVariable("categoryId") Integer categoryId){
		categoryService.deleteCategoryById(categoryId);
		return new ResponseEntity<>("Category "+categoryId+" deleted Successfully",HttpStatus.OK);
	}
	
	
}
