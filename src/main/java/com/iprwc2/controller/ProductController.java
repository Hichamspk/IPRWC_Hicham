package com.iprwc2.controller;

import com.iprwc2.DTO.ProductDTO;
import com.iprwc2.dao.ProductDao;
import com.iprwc2.DTO.ApiResponse;
import com.iprwc2.model.Category;
import com.iprwc2.model.Product;
import com.iprwc2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductDao productDao;
    @Autowired
    public ProductController(ProductDao productDao) {this.productDao = productDao;}
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/by-category/{categoryId}")
    public List<ProductDTO> getProductsByCategory(@PathVariable Long categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        List<Product> products = productRepository.findByCategory(category);

        // Converting to DTOs
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        // Set properties from product to dto
        return dto;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse addProduct(@RequestBody Product newProduct){
        Product product = this.productDao.addProduct(newProduct);
        return new ApiResponse(HttpStatus.OK, product);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
        Product updatedProduct = productDao.updateProduct(id, productDetails);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productDao.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
