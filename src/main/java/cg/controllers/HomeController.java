package cg.controllers;

import cg.model.Category;
import cg.model.Product;
import cg.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import cg.service.IProductService;

import java.util.ArrayList;

@Controller
@RequestMapping("/product")
public class HomeController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ModelAndView showProducts() {
        ModelAndView modelAndView = new ModelAndView("list");
        ArrayList<Product> products = productService.getAllProduct();
        if (products.isEmpty()) {
            modelAndView.addObject("message", "No products!");
            modelAndView.addObject("color", "red");
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("list");
        Product product = productService.getProduct(id);
        if (product != null) {
            productService.deleteProduct(product);
        } else {
            modelAndView.addObject("message", "Id invalid!");
            modelAndView.addObject("color", "red");
        }
        ArrayList<Product> products = productService.getAllProduct();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createProduct(Model model) {
        ModelAndView modelAndView = new ModelAndView("create");
        ArrayList<Category> categories = categoryService.getAllCategory();
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categories);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.getProduct(id);
        if (product != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        ArrayList<Category> categories = categoryService.getAllCategory();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.getProduct(id);
        if (product != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("create");
        Category category = categoryService.getCategory(Integer.parseInt(product.getCategory().getC_name()));
        product.setCategory(category);
        String message = productService.saveProduct(product);
        ArrayList<Product> products = productService.getAllProduct();
        modelAndView.addObject("products", products);
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
