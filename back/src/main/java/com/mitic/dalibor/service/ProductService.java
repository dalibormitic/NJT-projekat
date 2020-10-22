package com.mitic.dalibor.service;

import com.mitic.dalibor.model.Product;
import com.mitic.dalibor.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {
        this.productRepository.save(product);
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public void deleteById(long id) {
        this.productRepository.deleteById(id);
    }

    public Product findByProductName(String name){
        return this.productRepository.findByProductName(name);
    }

    public Optional<Product> findById(Long id){
        return this.productRepository.findById(id);
    }
}
