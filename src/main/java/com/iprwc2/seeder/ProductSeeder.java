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
        Category category3 = new Category(null, "DemonSlayer");
        // More categories...

        // Save categories
        category1 = categoryRepository.save(category1);
        category2 = categoryRepository.save(category2);
        category3 = categoryRepository.save(category3);
        // More saves...

        // Now create products with these categories
        Product product1 = new Product(null, category1,"Kite", "Kite funko pop", new Double("15.00"), "https://cdn.shopify.com/s/files/1/0433/1952/5529/products/61378_HunterxHunter_Kite_POP_GLAM-1-WEB.png?v=1661957863");
        Product product2 = new Product(null, category2,"Marco", "Marco funko pop", new Double("15.00"), "https://funko.com/dw/image/v2/BGTS_PRD/on/demandware.static/-/Sites-funko-master-catalog/default/dw395bbafb/images/funko/upload/74474-POP-Animation-One-Piece---Onami_GLAM-1-FW-WEB.png?sw=800&sh=800");
        Product product3 = new Product(null, category1,"Chopper", "Chopper funko pop", new Double("15.00"), "https://cdn.shopify.com/s/files/1/0433/1952/5529/products/5304_OnePiece_Chopper_GLAM_box.png?v=1662130350");
        Product product4 = new Product(null, category2,"Luffy", "Luffy funko pop", new Double("15.00"), "https://cdn.shopify.com/s/files/1/0433/1952/5529/products/5305_OnePiece_Luffy_GLAM_box.png?v=1662130928");
        Product product5 = new Product(null, category1,"Killua", "killua funko pop", new Double("15.00"), "https://cdn.shopify.com/s/files/1/0433/1952/5529/files/71493_POPAnimation_HxH_Killua_GW_GLAM-1-FW-WEB.png?v=1691055372");
        Product product6 = new Product(null, category2,"Gon", "Gon funko pop", new Double("15.00"), "https://cdn.shopify.com/s/files/1/0433/1952/5529/files/76701_HxH_GonFishing_POP_GLAM-1-FW-WEB.png?v=1703090968");
        Product product7 = new Product(null, category3,"Genya", "Genya funko pop", new Double("15.00"), "https://cdn.shopify.com/s/files/1/0433/1952/5529/files/72609PKG_DemonSlayer_S3_GenyaShinazugawa_POP_GLAM-1-WEB.png?v=1690968546");
        Product product8 = new Product(null, category3,"Tanjiro", "Tanjiro funko pop", new Double("15.00"), "https://cdn.shopify.com/s/files/1/0433/1952/5529/files/72613_POPAnimation_DemonSlayer_Tanjiro_Training__POP_GLAM-1.png?v=1695214325");
        Product product9 = new Product(null, category3,"Yabaha", "Yabaha funko pop", new Double("15.00"), "https://cdn.shopify.com/s/files/1/0433/1952/5529/files/73903PKG_DemonSlayer_S3_YahabaGW_POP_IE_GLAM-1-WEB.png?v=1690980399");
        Product product10 = new Product(null, category2,"Zenitsu", "Zenitsu funko pop", new Double("15.00"), "https://cdn.shopify.com/s/files/1/0433/1952/5529/files/76534a_POPAnimation_DemonSlayer_ZenitsuKneeling_GLAM-1-IE-WEB.png?v=1694610608");

        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);
        product3 = productRepository.save(product3);
        product4 = productRepository.save(product4);
        product5 = productRepository.save(product5);
        product6 = productRepository.save(product6);
        product7 = productRepository.save(product7);
        product8 = productRepository.save(product8);
        product9 = productRepository.save(product9);
        product10 = productRepository.save(product10);

    }
}
