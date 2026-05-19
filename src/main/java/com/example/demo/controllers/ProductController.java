package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product){
        return productService.createProduct(product); //passing the request to the service layer to create the product
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);

    }

}

//These are our REST endpoints which will enable the client to communicate with the backend

//When we follow the solid principles of MVC architecture, We will separate the Business-logic with the end-points

//Whatever the Entities, Repositories, Controllers(end-points) should go-through the Service layer(where our Business logic recides)

// CONTROLLER CLASS ONLY RESPONSIBILITY IS JUST TO MAP THE CORRECT ENDPOINTS AND HANDING OF ITS TASKS TO ITS SERVICE LAYER
// SERVICE LAYER's RESPONSIBILITY IS DOING THE BUSINESS LOGIC AND INTERACTING WITH DATABASE WHEN NECESSARY