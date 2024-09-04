package com.ecom.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Entities.Category;
import com.ecom.Entities.GenericResponseEntity;
import com.ecom.Exception.DataValidationException;
import com.ecom.Repository.CategoryRepository;
import com.ecom.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	@Override
	public GenericResponseEntity createCategory(Category category) {
		if (category.getName()== null || category.getName().isBlank()) {
			throw new DataValidationException("Category title required.");
		}
		categoryRepo.save(category);
		return new GenericResponseEntity(201, "Category created successfully.");
	}

	@Override
	public GenericResponseEntity updateCategory(Category category, Long categoryId) {
		if (category.getName() == null || category.getName().isBlank()) {
			throw new DataValidationException("Category name required.");
		}
		Category dbCategory = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new DataValidationException("Category Not Available."));

		dbCategory.setName(category.getName());
		dbCategory.setIsActive(category.getIsActive());

		categoryRepo.save(dbCategory);
		return new GenericResponseEntity(200, "Category updated successfully.");
	}

	@Override
	public GenericResponseEntity deleteCategory(Long categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new DataValidationException("Category Not Available."));
		categoryRepo.delete(category);
		return new GenericResponseEntity(202, "Category deleted successfully.");

	}

	@Override
	public Category getCategory(Long categoryId) {
		return categoryRepo.findById(categoryId)
				.orElseThrow(() -> new DataValidationException("Category Not Available."));
	}

	@Override
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}

}
