package com.learncode.blog.services;

import java.util.List;

import com.learncode.blog.payloads.CategoryDto;



public interface CategoryService {
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update 
	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
	
	//delete
	public void deleteCategory(Integer categoryId);
	
	//get
	
	CategoryDto getCategory(Integer categoryId);
	
	//get All
	List<CategoryDto> getCategories();

}
