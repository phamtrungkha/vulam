package com.lvl.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lvl.Entity.Product;


public interface ProductDAO extends JpaRepository<Product, Integer>{

}
