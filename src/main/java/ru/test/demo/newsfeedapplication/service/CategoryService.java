package ru.test.demo.newsfeedapplication.service;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.test.demo.newsfeedapplication.model.entities.Category;
import ru.test.demo.newsfeedapplication.model.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll(Sort.by("name"));
    }

    public ResponseEntity<?> getCategoryId() {
        return null;
    }

    public ResponseEntity<?> getCategoryAll() {
        return null;
    }

    public ResponseEntity<?> postCategoryAdd() {
        return null;
    }

    public ResponseEntity<?> putCategoryId() {
        return null;
    }

    public ResponseEntity<?> deleteCategoryId() {
        return null;
    }
}
