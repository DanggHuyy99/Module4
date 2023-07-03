package com.example.product.controller;

import com.example.product.Ulti.AppUtils;
import com.example.product.model.Product;
import com.example.product.repository.CategoryRepository;
import com.example.product.repository.ProductRepository;
import com.example.product.service.product.ProductService;
import com.example.product.service.product.request.ProductSaveRequest;
import com.example.product.service.product.request.SelectOptionRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @GetMapping("/create")
    public ModelAndView showCreateProduct() {
        ModelAndView view = new ModelAndView("create");
        view.addObject("categories", categoryRepository.findAll());
        view.addObject("product", new ProductSaveRequest("", "", new SelectOptionRequest("")));
        return view;
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") ProductSaveRequest product, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "create";
        }
        productService.create(product);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("product", product);
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String showEditProduct(Model model, @PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        return "edit";
    }

    @PostMapping("/edit")
    public String EditProduct(@Valid @ModelAttribute("updateProduct") Product updateProduct, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "edit";
        }
        Product product = productRepository.findById(updateProduct.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        product = AppUtils.mapper.map(updateProduct, Product.class);

        productService.update(product, updateProduct.getId());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("product", product);
        return "edit";
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "") String search, @PageableDefault(size = 5) Pageable pageable, Model model) {
        var productpage = productService.findAllWithSearchAndPaging(search, pageable);
        model.addAttribute("products", productpage);
        model.addAttribute("totalpages", productpage.getTotalPages());
        model.addAttribute("pageable", pageable);
        model.addAttribute("product", productService.findAllWithSearchAndPaging(search, pageable));
        return "list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String search, @PageableDefault(size = 5) Pageable pageable, Model model) {
        var productpage = productService.findAllWithSearchAndPaging(search, pageable);
        model.addAttribute("products", productpage);
        model.addAttribute("totalpages", productpage.getTotalPages());
        model.addAttribute("pageable", pageable);
        model.addAttribute("product", productService.findAllWithSearchAndPaging(search, pageable));
        return "list";
    }
}
