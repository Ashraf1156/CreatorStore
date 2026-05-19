package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //The Builder Design Pattern is a creational design pattern used to construct complex objects step-by-step
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //letting the DB to handle the generation of Ids of the products
    private Long id;

    @NotBlank(message = "Product name is required")//setting the validation that product name cannot be blank as well
    @Column(nullable = false) //setting the restriction that product name cannot be null
    private String name;

    private String description;

    private String category;

    @NotNull(message = "Price is required")//setting the validation that price should not be Null
    @DecimalMin(value = "0.0", inclusive = false, message = "price must be greater than 0")// setting the restriction that price value should always be greater than 0.0
    @Column(nullable = false) //Again the price of the product cannot be null
    private BigDecimal price;

    @NotNull(message = "stock quantity is required")//setting the validation that stock quantity should not be Null
    @Min(value=0, message = "stock cannot be less than 0")//setting the restriction that stock value cannot be less than 0
    @Column(name = "stock_quantity", nullable = false) //Here the column name should be in the snake-case, !camelcase
    private Integer stockQuantity;

    // To-Do:Relations
    // 3. One order will have Many Products so we will create the @OnetoMany Relationship
    // Here we should map the List of OrderItems to the Products
    /* When someone looks up a product (like a T-shirt), they only want to see the T-shirt's name, price, and stock.
       They do not want a massive list of every single customer order that contains that T-shirt.
       So, Here we are Setting the restriction to ignore order details when fetching a product, so we dont accidentally get all the orders associated with it
       */
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}


//First add the required annotations at the class level
//Second add the required annotations at the field level to take more control of their behaviour and impose some restrictions on them
