package cg.service.impl;

import cg.model.Category;
import cg.service.ICategoryService;

import java.util.ArrayList;

public class CategoryServiceImpl implements ICategoryService {
    private static final ArrayList<Category> categories;
    private static int id_auto = 0;

    static {
        categories = new ArrayList<>();
        categories.add(new Category(id_auto++, "SmartPhone"));
        categories.add(new Category(id_auto++, "Tablet"));
    }

    @Override
    public ArrayList<Category> getAllCategory() {
        return categories;
    }

    @Override
    public String saveCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategory(Category category) {

    }

    @Override
    public Category getCategory(int id) {
        for (Category c : categories) {
            if (c.getC_id() == id) {
                return c;
            }
        }
        return null;
    }
}
