package com.ecom.product.service;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.product.entity.Category;
import com.ecom.product.repository.CategoryRepo;
import com.ecom.product.util.FileUtils;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	public Category addCategory(Category category, MultipartFile multipartFile) throws IOException {
		 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 String fileCode = FileUtils.saveFile(fileName, multipartFile,"category_img");
		 category.setCategoryImage(fileCode+"-"+fileName);
		return categoryRepo.save(category);
	}
	
	public Category updateCategory(Category category, MultipartFile multipartFile) throws IOException {
		categoryRepo.findById(category.getCategoryId()).orElseThrow(
				()->new RuntimeException("Category not found with "+category.getCategoryId()));
		 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 String fileCode = FileUtils.saveFile(fileName, multipartFile,"category_img");
		 category.setCategoryImage(fileCode+"-"+fileName);
		return categoryRepo.save(category);
	}
	
	public Category getCategoryById(Integer categoryId) {
		return categoryRepo.findById(categoryId).get();
	}
	
	public Page<Category> getCategories(Category category,Pageable pageable){
		return null;
	}
	
	public List<Category> getCategories(){
		return categoryRepo.findAll();
	}
	
	public void deleteCategoryById(Integer categoryId) {
		categoryRepo.deleteById(categoryId);
	}
	
	
}
