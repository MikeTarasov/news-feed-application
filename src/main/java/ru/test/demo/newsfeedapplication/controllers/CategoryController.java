package ru.test.demo.newsfeedapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test.demo.newsfeedapplication.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryId(@PathVariable long id) {
        return categoryService.getCategoryId();
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
