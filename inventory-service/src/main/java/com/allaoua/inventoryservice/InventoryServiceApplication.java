package com.allaoua.inventoryservice;

import com.allaoua.inventoryservice.entity.*;
import com.allaoua.inventoryservice.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CorsConfiguration corsConfiguration(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        return corsConfiguration;
    }

//    @Bean
//    CommandLineRunner start(CategoryRepository categoryRepository,
//                            ProductRepository productRepository,
//                            BrandRepository brandRepository,
//                            ColorRepository colorRepository,
//                            SizeRepository sizeRepository) {
//        return args -> {
//            categoryRepository.deleteAll();
//            brandRepository.deleteAll();
//            colorRepository.deleteAll();
//            productRepository.deleteAll();
//
//            List.of("Vetements", "Sport", "Legumes", "fruits").forEach(name -> {
//                categoryRepository.save(Category.builder()
//                        .name(name)
//                        .description("Description " + name)
//                        .imageURL("file:\\D:\\e-commerce_mobile_frontend_FLUTTER\\assets\\fruits.png")
//                        .build());
//            });
//
//            List.of("Adidas", "Puma", "Nike", "Fashion").forEach(name -> {
//                brandRepository.save(Brand.builder()
//                        .name(name)
//                        .build());
//            });
//
//            List.of("Rouge", "Blue", "Marron", "Jaune", "Violet").forEach(name -> {
//                colorRepository.save(Color.builder()
//                        .color(name)
//                        .build());
//            });
//
//            List.of("S", "X", "XL", "XXL", "3XL", "4XL").forEach(name -> {
//                sizeRepository.save(Size.builder()
//                        .label(name)
//                        .build());
//            });
//            List<Brand> allBrands = brandRepository.findAll();
//            List<Color> allColors = colorRepository.findAll();
//            List<Size> allSizes = sizeRepository.findAll();
//            categoryRepository.findAll().forEach(category -> {
//                allBrands.forEach(brand -> {
//                    Product product=Product.builder()
//                            .quantity( (int)Math.round(Math.random()*100))
//                            .price(Math.random()*400)
//                            .oldPrice(Math.random()*500)
//                            .name("product")
//                            .brand(brand)
//                            .sizes(allSizes)
//                            .colors(allColors)
//                            .description("une description")
//                            .category(category)
//                            .build();
//                    productRepository.save(product);
//
//                });
//
//            });
//        };
//    }

}
