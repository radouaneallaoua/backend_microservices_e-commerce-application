package com.allaoua.inventoryservice.service;

import com.allaoua.inventoryservice.dto.CategoryRequestDto;
import com.allaoua.inventoryservice.dto.CategoryResponseDto;
import com.allaoua.inventoryservice.dto.ProductRequestDto;
import com.allaoua.inventoryservice.dto.ProductResponseDto;
import com.allaoua.inventoryservice.entity.*;
import com.allaoua.inventoryservice.exception.*;
import com.allaoua.inventoryservice.repository.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService {
    final private ProductRepository productRepository;
    final private CategoryRepository categoryRepository;
    final private BrandRepository brandRepository;
    private final ProductColorRepository productColorRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ProductSizeRepository productSizeRepository;
    private final ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository, ProductColorRepository productColorRepository, ColorRepository colorRepository, SizeRepository sizeRepository, ProductSizeRepository productSizeRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productColorRepository = productColorRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
        this.productSizeRepository = productSizeRepository;
        this.productImageRepository = productImageRepository;
    }

    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll().stream().map(Product::toDto).toList());
    }


    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithCategoryId(Long categoryId) {
        return ResponseEntity.ok(productRepository.findByCategoryId(categoryId).stream().map(Product::toDto).toList());
    }

    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithCategoryIdAndPage(Long categoryId, int pageNumber,int pageSize) {
        return ResponseEntity.ok(productRepository.findByCategoryId(categoryId,Pageable.ofSize(pageSize).withPage(pageNumber)).stream().map(Product::toDto).toList());
    }

    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithPriceBetweenAndPage(double minPrice,double maxPrice,  int pageNumber,int pageSize) {
        return ResponseEntity.ok(productRepository.findByPriceBetween(minPrice,maxPrice,Pageable.ofSize(pageSize).withPage(pageNumber)).stream().map(Product::toDto).toList());
    }

    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithCategoryIdAndPriceDesc(Long categoryId) {
        return ResponseEntity.ok(productRepository.findByCategoryId(categoryId,Sort.by(Sort.Direction.DESC,"price")).stream().map(Product::toDto).toList());
    }

    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithBrandId(Long brandId) {
        return ResponseEntity.ok(productRepository.findByBrandId(brandId).stream().map(Product::toDto).toList());
    }

    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithCategoryIdAndPriceAsc(Long categoryId) {
        return ResponseEntity.ok(productRepository.findByCategoryId(categoryId,Sort.by(Sort.Direction.ASC,"price")).stream().map(Product::toDto).toList());
    }

    public ResponseEntity<List<ProductResponseDto>> getAllProductsByNameContaining(String name) {
        return ResponseEntity.ok(productRepository.findByNameContainingIgnoreCase(name).stream().map(Product::toDto).toList());
    }

    public ResponseEntity<ProductResponseDto> saveProduct(ProductRequestDto productRequestDto) throws IOException {
        Product product=Product.builder()
                .id(UUID.randomUUID().toString())
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .oldPrice(productRequestDto.getOldPrice())
                .brand(brandRepository.findById(productRequestDto.getBrandId()).orElseThrow(()-> new BrandNotFoundException("brand not found with id "+productRequestDto.getBrandId())))
                .category(categoryRepository.findById(productRequestDto.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("category not found with id "+productRequestDto.getCategoryId())))
                .build();
        Product savedProduct=productRepository.save(product);

        List<ProductColor> productColors=new ArrayList<>();
        if(productRequestDto.getColorsIds()!=null){
            productRequestDto.getColorsIds().forEach(c->{
                ProductColor productColor=ProductColor.builder()
                        .color(colorRepository.findById(c).orElseThrow(()-> new ColorNotFoundException("color not found with id "+c)))
                        .product(savedProduct)
                        .productColorKey(ProductColorKey.builder().colorId(c).productId(savedProduct.getId()).build())
                        .build();
                ProductColor savedColor = productColorRepository.save(productColor);
                productColors.add(savedColor);
            });
        }
        List<ProductSize> productSizes=new ArrayList<>();
        if(productRequestDto.getSizesIds()!=null){
            productRequestDto.getSizesIds().forEach(s->{
                ProductSize productSize=ProductSize.builder()
                        .size(sizeRepository.findById(s).orElseThrow(()-> new SizeNotFoundException("size not found with id "+s)))
                        .product(savedProduct)
                        .productSizeKey(ProductSizeKey.builder().productId(savedProduct.getId()).sizeId(s).build())
                        .build();
                ProductSize savedSize = productSizeRepository.save(productSize);
                productSizes.add(savedSize);
            });
        }

        Path path= Paths.get(System.getProperty("user.home"),"e-comm-final","products_images");
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        List<ProductImage> productImages=new ArrayList<>();
        if(productRequestDto.getImages()!=null){
            productRequestDto.getImages().forEach(img->{
                String imageId=UUID.randomUUID().toString();
                //String extension=c.getOriginalFilename().substring(c.getOriginalFilename().lastIndexOf("."));
                Path imagePath=Paths.get(System.getProperty("user.home"),"e-comm-final","products_images",imageId+".webp");
                try {
                    Files.copy(img.getInputStream(),imagePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ProductImage productImage= ProductImage.builder()
                        .imageURL(imagePath.toUri().toString())
                        .product(savedProduct)
                        .build();
                ProductImage savedImage = productImageRepository.save(productImage);
                productImages.add(savedImage);
            });
        }


        savedProduct.setImages(productImages);
        savedProduct.setColors(productColors);
        savedProduct.setProductSizes(productSizes);
        return ResponseEntity.ok(productRepository.save(savedProduct).toDto());
    }





    public ResponseEntity<ProductResponseDto> getProductById(String productId) {
        Product product=productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("product not found with id "+productId));
        return ResponseEntity.ok(product.toDto());
    }


    public ResponseEntity<String> deleteProduct(String productId) {
       productRepository.deleteById(productId);
        return ResponseEntity.ok("product successfully deleted");
    }


    public ResponseEntity<ProductResponseDto> updateProduct(String productId,
                                                            String name,
                                                            String description,
                                                            double price,
                                                            double oldPrice,
                                                            int quantity,
                                                            Long categoryId,
                                                            Long brandId,
                                                            List<MultipartFile> images,
                                                            List<Long> colorsIds,
                                                            List<Long> sizesIds) {
        Product product=productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("product not found with id "+productId));
        if(name!=null){
            product.setName(name);
        }
        if(description!=null){
            product.setDescription(description);
        }
        if(price!=0){
            product.setPrice(price);
        }
        if(oldPrice!=0){
            product.setOldPrice(oldPrice);
        }
        if(quantity!=0){
            product.setQuantity(quantity);
        }
        if(categoryId!=null){
            Category category=categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("category not found with id "+categoryId));
            product.setCategory(category);
        }
        if(brandId!=null){
            Brand brand=brandRepository.findById(brandId).orElseThrow(()-> new BrandNotFoundException("brand not found with id "+brandId));
            product.setBrand(brand);
        }
        if(images!=null){
            product.getImages().forEach(pm->{
                try {
                    Files.delete(Path.of(URI.create(pm.getImageURL())));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            List<ProductImage> productImages=new ArrayList<>();
            images.forEach(img->{
                String imageId=UUID.randomUUID().toString();
                //String extension=c.getOriginalFilename().substring(c.getOriginalFilename().lastIndexOf("."));
                Path imagePath=Paths.get(System.getProperty("user.home"),"e-comm-final","products_images",imageId+".webp");
                try {
                    Files.copy(img.getInputStream(),imagePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ProductImage productImage= ProductImage.builder()
                        .imageURL(imagePath.toUri().toString())
                        .product(product)
                        .build();
                productImages.add(productImage);
            });
            product.setImages(productImages);
        }

        if(colorsIds!=null){

            List<ProductColor> productColors=new ArrayList<>();
            colorsIds.forEach(c->{
                ProductColor productColor=ProductColor.builder()
                        .color(colorRepository.findById(c).orElseThrow(()-> new ColorNotFoundException("color not found with id "+c)))
                        .product(product)
                        .build();
                productColors.add(productColor);
            });
            product.setColors(productColors);


        }
        if(sizesIds!=null){
            List<ProductSize> productSizes=new ArrayList<>();
            sizesIds.forEach(s->{
                ProductSize productSize=ProductSize.builder()
                        .size(sizeRepository.findById(s).orElseThrow(()-> new SizeNotFoundException("size not found with id "+s)))
                        .product(product)
                        .build();
                productSizes.add(productSize);
            });
            product.setProductSizes(productSizes);
        }
        return ResponseEntity.ok(productRepository.save(product).toDto());
    }
}
