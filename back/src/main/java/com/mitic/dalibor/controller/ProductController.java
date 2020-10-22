package com.mitic.dalibor.controller;

import com.mitic.dalibor.dto.ProductDto;
import com.mitic.dalibor.model.Product;
import com.mitic.dalibor.model.User;
import com.mitic.dalibor.service.ProductService;
import com.mitic.dalibor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts(){
        List<Product> products  = productService.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product: products) {
            userService.findById(product.getCreated_by()).ifPresent(user -> productDtos.add(new ProductDto(product.getProductId(), product.getProduct_name(), product.getProduct_price(), product.getMeasurement_unit(), user.getUsername())));
        }
        return productDtos;
    }

    @PostMapping("/products")
    public ResponseEntity<Boolean> addProduct(@RequestBody Product product){
        Product productFromDB = this.productService.findByProductName(product.getProduct_name());
        if(productFromDB == null) {
            productService.save(product);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<Boolean> updateProduct(@PathVariable long id, @RequestBody Product product) {
        Product productFromDB = productService.findById(id).orElse(null);
        if(productFromDB != null){
            productFromDB.setProduct_name(product.getProduct_name());
            productFromDB.setProduct_price(product.getProduct_price());
            productFromDB.setMeasurement_unit(product.getMeasurement_unit());
            this.productService.save(productFromDB);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
