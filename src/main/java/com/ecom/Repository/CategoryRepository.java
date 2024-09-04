package com.ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecom.Entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
