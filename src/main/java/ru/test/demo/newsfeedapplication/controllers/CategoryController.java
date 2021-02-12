package ru.test.demo.newsfeedapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.test.demo.newsfeedapplication.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public String getCategoryId(@PathVariable long id, Model model) {
        return categoryService.getCategoryId(id, model);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCategoryAll() {
        return categoryService.getCategoryAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> postCategoryAdd(@RequestParam String name) {
        return categoryService.postCategoryAdd();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCategoryId(@PathVariable long id, @RequestParam String name) {
        return categoryService.putCategoryId();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryId(@PathVariable long id) {
        return categoryService.deleteCategoryId();
    }
}
