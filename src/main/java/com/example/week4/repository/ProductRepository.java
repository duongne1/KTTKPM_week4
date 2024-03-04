package com.example.week4.repository;

import com.example.week4.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
