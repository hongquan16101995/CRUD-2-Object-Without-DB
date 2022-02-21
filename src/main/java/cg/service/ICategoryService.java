package cg.service;

import cg.model.Category;

import java.util.ArrayList;

public interface ICategoryService {
    ArrayList<Category> getAllCategory();

    String saveCategory(Category category);

    void deleteCategory(Category category);

    Category getCategory(int id);
}
