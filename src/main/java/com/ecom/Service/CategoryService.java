package com.ecom.Service;

import java.util.List;

import com.ecom.Entities.Category;
import com.ecom.Entities.GenericResponseEntity;

public interface CategoryService {

	GenericResponseEntity createCategory(Category category);

	GenericResponseEntity updateCategory(Category category, Long categoryId);

	GenericResponseEntity deleteCategory(Long categoryId);

	Category getCategory(Long categoryId);

	List<Category> getCategories();

}
