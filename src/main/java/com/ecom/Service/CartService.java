package com.ecom.Service;

import com.ecom.Entities.GenericResponseEntity;

public interface CartService {

	GenericResponseEntity saveCart(Integer pid, Integer uid);

}
