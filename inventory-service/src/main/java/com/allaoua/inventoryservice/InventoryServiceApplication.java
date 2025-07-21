package com.allaoua.inventoryservice;

import com.allaoua.inventoryservice.entity.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
        Product product= Product.builder()
                .id("ee")
                .description("ddd")
                .name("eee")
                .price(122)
                .oldPrice(222)
                .quantity(122)
                .build();
    }

}
