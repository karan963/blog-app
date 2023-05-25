package com.workspace.blog.service;

import java.util.List;

import com.workspace.blog.payloads.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	CategoryDto getCategory(Integer categoryId);
	List<CategoryDto> getCategories();
	void deleteCategory(Integer categoryId);
}
