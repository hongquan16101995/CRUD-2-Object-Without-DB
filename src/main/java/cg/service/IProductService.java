package cg.service;

import cg.model.Product;

import java.util.ArrayList;

public interface IProductService {
    ArrayList<Product> getAllProduct();

    String saveProduct(Product product);

    void deleteProduct(Product product);

    Product getProduct(int id);
}
