package com.iprwc2.seeder;

import com.iprwc2.model.Category;
import com.iprwc2.model.Product;
import com.iprwc2.repository.CategoryRepository;
import com.iprwc2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ProductSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository; // Add this

    @Override
    public void run(String... args) throws Exception {
        // Create categories
        Category category1 = new Category(null, "HunterxHunter");
        Category category2 = new Category(null, "OnePiece");
        // More categories...

        // Save categories
        category1 = categoryRepository.save(category1);
        category2 = categoryRepository.save(category2);
        // More saves...

        // Now create products with these categories
        Product product1 = new Product(null, category1,"Kite", "Kite funko pop", new Double("15.00"), "https://funkoeurope.com/cdn/shop/products/61378_HunterxHunter_Kite_POP_GLAM-WEB_600x.png?v=1652883795");
        Product product2 = new Product(null, category2,"Marco", "Marco funko pop", new Double("15.00"), "https://funkoeurope.com/cdn/shop/files/74474-POP-Animation-One-Piece---Marco_GLAM-WEB_600x.png?v=1694609080");

        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);

    }
}
