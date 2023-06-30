package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private static int ID;
    private static List<Category> categories = new ArrayList<>();

    static {
        categories.add(new Category(ID++, "Tủ Lạnh"));
        categories.add(new Category(ID++, "Gái Xinh"));
    }
    public List<Category> getCategories(){
        return categories;
    }

    public Category findById(int id) {
        for (Category cate : categories) {
            if (cate.getId() == id) {
                return cate;
            }
        }
        return null;
    }
}
