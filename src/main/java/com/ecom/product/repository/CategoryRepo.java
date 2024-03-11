package com.ecom.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.product.entity.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
