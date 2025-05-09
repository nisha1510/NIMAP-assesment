package com.demo.SpringBootJPA.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.SpringBootJPA.Entity.Product;
import com.demo.SpringBootJPA.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
   
//    public Page<Product> getAllProducts(int page, int size) {
//        return productRepository.findAll(PageRequest.of(page, size));
//    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setCategory(productDetails.getCategory());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

   public void deleteProduct(Long id) {
	   productRepository.deleteById(id);
   }
}

