package com.iprwc2.dao;

import com.iprwc2.model.Category;
import com.iprwc2.model.Product;
import com.iprwc2.repository.CategoryRepository;
import com.iprwc2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDao {

    @Autowired
    private CategoryRepository categoryRepository ;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
