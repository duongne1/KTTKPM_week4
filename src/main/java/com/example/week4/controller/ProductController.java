package com.example.week4.controller;

import com.example.week4.entity.Product;
import com.example.week4.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    private Jedis jedis = new Jedis();

@GetMapping("/products")
private List<Product> getList(){
    List<Product> list = productRepository.findAll();
    return list;
}

@GetMapping("/products/{id}")
public Product getProductById(@PathVariable(value = "id") int id){
    String key = String.valueOf(id);
    if (jedis.exists(key)) {
        Product productCash = new Product();
        productCash.setId(id);
        String userName = jedis.get(key);
        productCash.setName(userName);
        return productCash;
    } else {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User_id " + id + " not found"));

        jedis.setex(key, 3600, product.getName());
        System.out.println("Saved in cache");
        return product;
    }
}

@DeleteMapping("/products/{id}")
private void deleteProduct(@PathVariable(value = "id") int id){
    Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    productRepository.delete(product);
    jedis.del(String.valueOf(product.getId()));
    System.out.println("Delete in cache");
}

@PostMapping("/products")
private Product addProduct(@RequestBody Product product){
    jedis.set(String.valueOf(product.getId()), product.getName());
    System.out.println("saved in cache");
    return productRepository.save(product);
}

@PutMapping("products/{id}")
public Product updateProduct(@PathVariable(value = "id") int id, @RequestBody Product product) {
    Product productUpdate = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    productUpdate.setName(product.getName());
    jedis.set(String.valueOf(product.getId()),product.getName());
    return productRepository.save(productUpdate);
}


}
