package cg.service.impl;

import cg.model.Category;
import cg.model.Product;
import cg.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cg.service.IProductService;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ICategoryService categoryService;

    private static final ArrayList<Product> products = new ArrayList<>();
    private static int id_auto = 0;

    @Override
    public ArrayList<Product> getAllProduct() {
        return products;
    }

    @Override
    public String saveProduct(Product product) {
        if (product.getId() == 0) {
            return persist(product);
        } else {
            return merge(product);
        }
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
    }

    @Override
    public Product getProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private String persist(Product product) {
        product.setId(++id_auto);
        products.add(product);
        return "Create Successfully!";
    }

    private String merge(Product product) {
        int index = 0;
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                index = products.indexOf(p);
            }
        }
        products.set(index, product);
        return "Update Successfully!";
    }
}
