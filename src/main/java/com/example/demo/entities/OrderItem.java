package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "price_at_purchase",nullable = false)
    private BigDecimal priceAtPurchase;

    //To-Do: relations
    //2.Accepting the relation of @OnetoMany and replying back with the @ManytoOne and joining the column with order_id
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    //To-Do: relations
    //4.Accepting the relation of @OnetoMany and replying back with the @ManytoOne and joining the column with product_id as foreign Key
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


}

