package com.ecom.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Entities.GenericResponseEntity;
import com.ecom.Entities.Product;
import com.ecom.Exception.DataValidationException;
import com.ecom.Repository.ProductRepository;
import com.ecom.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	
	
	@Override
	public GenericResponseEntity addProduct(Product product) {
		if (product.getTitle()== null || product.getTitle().isBlank()) {
			throw new DataValidationException("Product title required.");
		}
		if(product.getCategory() == null || product.getCategory().getId() ==null ) {
			throw new DataValidationException("Product Category Required.");
		}
		if(product.getPrice() == null) {
			throw new DataValidationException("Product price required.");
		}
		if(product.getStock() == null) {
			throw new DataValidationException("Product price required.");
		}
		if(product.getIsActive() == null) {
			throw new DataValidationException("Product status required.");
		}
		productRepo.save(product);
		return new GenericResponseEntity(201, "Product Added successfully.");
	}

	@Override
	public GenericResponseEntity updateProduct(Product product, Long productId) {
		if (product.getTitle()== null || product.getTitle().isBlank()) {
			throw new DataValidationException("Product title required.");
		}
		Product dbProduct = this.productRepo.findById(productId)
				.orElseThrow(() -> new DataValidationException("Product Not Available."));

		dbProduct.setTitle(product.getTitle());
		dbProduct.setDescription(product.getDescription());
		dbProduct.setCategory(product.getCategory());
		dbProduct.setPrice(product.getPrice());
		dbProduct.setStock(product.getStock());
		dbProduct.setImage(product.getImage());
		dbProduct.setDiscount(product.getDiscount());
		dbProduct.setDiscountPrice(product.getDiscountPrice());
		dbProduct.setIsActive(product.getIsActive());
		productRepo.save(dbProduct);
		return new GenericResponseEntity(200, "Product updated successfully.");
	}

	@Override
	public GenericResponseEntity deleteProduct(Long productId) {
		Product product = this.productRepo.findById(productId)
				.orElseThrow(() -> new DataValidationException("Product Not Available."));
		productRepo.delete(product);
		return new GenericResponseEntity(202, "Product deleted successfully.");

	}

	@Override
	public Product getProduct(Long productId) {
		return productRepo.findById(productId)
				.orElseThrow(() -> new DataValidationException("Product Not Available."));
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

}
