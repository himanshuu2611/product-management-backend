package com.himanshu.Product.Management.System.repository;

import com.himanshu.Product.Management.System.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}