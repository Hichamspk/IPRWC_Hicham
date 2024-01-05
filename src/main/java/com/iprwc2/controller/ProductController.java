package com.iprwc2.controller;

import com.iprwc2.dao.ProductDao;
import com.iprwc2.model.ApiResponse;
import com.iprwc2.model.AuthenticationResponse;
import com.iprwc2.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductDao productDao;
    @Autowired
    public ProductController(ProductDao productDao) {this.productDao = productDao;}

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse addProduct(@RequestBody Product newProduct){
        Product product = this.productDao.addProduct(newProduct);
        return new ApiResponse(HttpStatus.ACCEPTED, product);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
