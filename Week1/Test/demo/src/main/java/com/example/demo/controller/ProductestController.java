package com.example.demo.controller;

import com.example.demo.model.ProductTest;
import com.example.demo.repository.CategoryRespository;
import com.example.demo.repository.ProductTestRepository;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/productest")
public class ProductestController {
    @Autowired
    private ProductTestRepository productTestRepository;
    @Autowired
    private CategoryRespository categoryRespository;
    @Autowired
    private  ProductService productService;

    public void ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @PostMapping("/save")
//    public String saveData(Category category){
//        productTestRepository.save(new Category())
//    }

//    @GetMapping
//    public String ListProduct(Model model) {
//        model.addAttribute("products", productTestRepository.findAll());
//        return "listproductest";
//    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String search,Model model,@PageableDefault(size = 5, sort = {"price"})  Pageable pageable){
//        search = "%" + search + "%";
        var productPage = productTestRepository.findByTittleContainingOrCodeContainingOrCategory_NameContaining(search, search, search, pageable);
        model.addAttribute("productss", productPage);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("pageable",pageable);
        return "listproductest";
    }

    @GetMapping("/createproduct")
    public String showCreateForm(Model model){
        model.addAttribute("products", new ProductTest());
        model.addAttribute("categories", categoryRespository.findAll());
        return "createproduct";
    }

    @PostMapping("/createproduct")
    public String createProduct(@Valid @ModelAttribute("products") ProductTest products, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryRespository.findAll());
            return "createproduct";
        }
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryRespository.findAll());

        productTestRepository.save(products);
        return "createproduct";
    }

    @GetMapping("/editproduct/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        ProductTest productTest = productTestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id"));
        model.addAttribute("product", productTest);
        model.addAttribute("categories", categoryRespository.findAll());
        return "editproduct";
    }

    @PostMapping("/editproduct")
    public String editProduct(@Valid @ModelAttribute("updateProductest") ProductTest updateProductest, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            return "editproduct";
        }

        ProductTest productTest = productTestRepository.findById(updateProductest.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid product Id"));

        productTest.setCode(updateProductest.getCode());
        productTest.setPrice(updateProductest.getPrice());
        productTest.setTittle(updateProductest.getTittle());
        productTest.setCategory(updateProductest.getCategory());

        model.addAttribute("product", productTest);
        model.addAttribute("categories", categoryRespository.findAll());
        productTestRepository.save(productTest);

        return "editproduct";
    }

    @GetMapping("/deleteproduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
        ProductTest productTest = productTestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id"));

        productTestRepository.delete(productTest);

        return "redirect:/productest";
    }

    @GetMapping
    public String getProductList(Model model, Pageable pageable, @RequestParam(defaultValue = "") String search) {

        int pageSize = 3;
        pageable = PageRequest.of(pageable.getPageNumber(), pageSize);

        Page<ProductTest> productPage = productService.getAllProducts(pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", productPage.getNumber());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("productss", productPage);
        model.addAttribute("pageable",pageable);


        return "listproductest";
    }

}
