package com.lvl.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvl.Entity.Category;
import com.lvl.Repository.CategoryRepository;

@Service
public class CategoriesService {
	@Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
    	return categoryRepository.findAll();
    }
}
