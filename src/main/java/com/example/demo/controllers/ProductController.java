package com.example.demo.controllers;

import com.example.demo.entities.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {


    @PostMapping
    public Product createProduct(@Valid Product product){
        return null;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product){
        return null;
    }

    @GetMapping
    public List<Product> getProducts(){
        return null;
    }

    @GetMapping("/{id}")
    public void getProductById(@PathVariable Long Id){

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long Id){

    }

}

//These are our REST endpoints which will enable the client to communicate with the backend

//When we follow the solid principles of MVC architecture, We will separate the Business-logic with the end-points

//Whatever the Entities, Repositories, Controllers(end-points) should go-through the Service layer(where our Business logic recides)