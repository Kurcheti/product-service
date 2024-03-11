package com.ecom.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.product.entity.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
