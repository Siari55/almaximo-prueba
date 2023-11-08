package com.crud.products.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.products.models.Product;

@Repository
public interface ProductDao extends CrudRepository<Product, Long> {

	List<Product> findByBstatus(Boolean bstatus);
	
}
