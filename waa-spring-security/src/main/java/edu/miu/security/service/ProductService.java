package edu.miu.security.service;

import edu.miu.security.entity.Product;
import edu.miu.security.entity.Review;
import edu.miu.security.model.ProductRequest;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> getAll();

    Product getById(int id);

    void add(ProductRequest productRequest);

    List<Review> getReviews(int id);

    void addReviews(Set<Review> reviews, int id);
}
