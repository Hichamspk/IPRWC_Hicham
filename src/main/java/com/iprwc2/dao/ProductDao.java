package com.iprwc2.dao;

import com.iprwc2.model.Product;
import com.iprwc2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {

    @Autowired
    private ProductRepository productRepository;


    public Product addProduct(Product newProduct) {
        Product product = this.productRepository.save(newProduct);
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
