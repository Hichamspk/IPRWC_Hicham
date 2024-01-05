package com.iprwc2.seeder;

import com.iprwc2.model.Product;
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

    @Override
    public void run(String... args) throws Exception {
        // Voeg hier producten toe
        Product product1 = new Product(null, "Kite", "HunterxHunter", "Kite funko pop", new Double("15.00"),  "https://funkoeurope.com/cdn/shop/products/61378_HunterxHunter_Kite_POP_GLAM-WEB_600x.png?v=1652883795");
        Product product2 = new Product(null, "Product 2", "OnePiece", "Marco funko pop", new Double("3.50"),  "https://funkoeurope.com/cdn/shop/files/74474-POP-Animation-One-Piece---Marco_GLAM-WEB_600x.png?v=1694609080");
        Product product3 = new Product(null, "Product 3", "Categorie 3", "Beschrijving 3", new Double("5.99"),  "image_url");
        Product product4 = new Product(null, "Product 4", "Categorie 4", "Beschrijving 4", new Double("15.00"),  "image_url");
        Product product5 = new Product(null, "Product 5", "Categorie 5", "Beschrijving 5", new Double("2.50"),  "image_url");




        // Creëer meer producten...
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        // Sla andere producten op...
    }
}
