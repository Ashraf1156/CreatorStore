package com.example.demo.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is required")
    @Column(name= "customer_name", nullable = false)
    private String customerName;

    @NotBlank(message = "Customer email is required")
    @Column(name = "customer_email",nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String status;

    @NotNull(message = "total Price is required")//setting the validation that price should not be Null
    @DecimalMin(value = "0.0", inclusive = false, message = "total price must be greater than 0")// setting the restriction that price value should always be greater than 0.0
    @Column(name = "total_price",nullable = false)
    private BigDecimal totalPrice;

// Every order will have multiple OrderItems, so the relation would be OnetoMany
// In real-world, We will keep all the list of the items in the list so like that only here also we are keeping all the items in one list
// Separate list of orderItems would be created for each Order
// Here we should map the List of OrderItems to the Orders
// 1. Creating the Relation
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }



}
