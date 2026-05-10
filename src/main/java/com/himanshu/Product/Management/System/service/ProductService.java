package com.himanshu.Product.Management.System.service;

import com.himanshu.Product.Management.System.entity.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(String id);

    Product updateProduct(String id, Product product);

    void deleteProduct(String id);
}