package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}