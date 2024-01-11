package com.iprwc2.dao;

import com.iprwc2.model.Category;
import com.iprwc2.model.Product;
import com.iprwc2.repository.CategoryRepository;
import com.iprwc2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Product addProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setDescription(productDetails.getDescription());
                    product.setPrice(productDetails.getPrice());
                    product.setImageUrl(productDetails.getImageUrl());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
