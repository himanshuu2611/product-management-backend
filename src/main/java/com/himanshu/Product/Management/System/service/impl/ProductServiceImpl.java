package com.himanshu.Product.Management.System.service.impl;

import com.himanshu.Product.Management.System.entity.Product;
import com.himanshu.Product.Management.System.repository.ProductRepository;
import com.himanshu.Product.Management.System.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {

        product.setCreatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product updateProduct(String id, Product updatedProduct) {

        Product existingProduct = getProductById(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(String id) {

        Product product = getProductById(id);

        productRepository.delete(product);
    }
}