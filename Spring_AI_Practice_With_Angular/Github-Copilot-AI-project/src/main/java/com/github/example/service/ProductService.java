package com.github.example.service;

import com.github.example.dto.Products;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public List<Products> getAllProducts() {
        return List.of(
                new Products("Product 1", 10.0, 100),
                new Products("Product 2", 20.0, 200),
                new Products("Product 3", 30.0, 300)
        );
    }

}
