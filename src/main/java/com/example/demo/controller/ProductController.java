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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Hiển thị danh sách sản phẩm
    @GetMapping
    public String list(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/products";
    }

    // Mở form thêm sản phẩm
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/add";
    }

    // Lưu sản phẩm mới hoặc cập nhật
    @PostMapping("/save")
    public String save(@Valid Product product, BindingResult result, @RequestParam(value = "imageProduct", required = false) MultipartFile imageProduct, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "product/add";
        }

        // Xử lý upload hình ảnh nếu có
        if (imageProduct != null && !imageProduct.isEmpty()) {
            productService.updateImage(product, imageProduct);
        }

        // Lấy category từ database
        Category selectedCategory = categoryService.getCategoryById(product.getCategory().getId());
        product.setCategory(selectedCategory);

        // Lưu sản phẩm
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Mở form sửa sản phẩm
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "error/404";
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/add";
    }

    // Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
        }
        return "redirect:/products";
    }
}
