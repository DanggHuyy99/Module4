package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class HomeController {
    @Autowired
    private final ProductService productService;

    @Autowired
    private final CategoryService categoryService;

    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("message", "List Product");
        List<Product> productList = productService.getProducts();
        model.addAttribute("products", productList);
        return "index";
    }

//    @GetMapping("/{id}")
//    public String getIndex(Model model, @PathVariable int id) {
//        model.addAttribute("message", "demo");
//        model.addAttribute("id", id);
//        return "index";
//    }


    @GetMapping("/create")
    public String showProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getCategories());
        return "create";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute Product product, BindingResult bindingResul, Model model) {
        if (bindingResul.hasErrors()) {
            model.addAttribute("categories", categoryService.getCategories());
            return "create";
        }
        List<Category> categories = categoryService.getCategories();
        Category category = categoryService.findById(product.getCategory().getId());
        product.setCategory(category);
        productService.add(product);
        model.addAttribute("categories", categories);
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable int id) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getCategories());
        return "edit";
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getCategories());
            return "edit";
        }
        Product productUD = productService.findProductById(product.getId());
        productUD.setName(product.getName());
        productUD.setPrice(product.getPrice());
        Category category = categoryService.findById(product.getCategory().getId());
        productUD.setCategory(category);
//        productService.updateProduct(productUD);
        model.addAttribute("categories", categoryService.getCategories());
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
