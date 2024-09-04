package com.ecom.Service;

import java.util.List;

import com.ecom.Entities.GenericResponseEntity;
import com.ecom.Entities.Product;

public interface ProductService {

	GenericResponseEntity addProduct(Product product);

	GenericResponseEntity updateProduct(Product product, Long productId);

	GenericResponseEntity deleteProduct(Long productId);

	Product getProduct(Long productId);

	List<Product> getAllProducts();

}
