package ru.test.demo.newsfeedapplication.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.test.demo.newsfeedapplication.model.repositories.CategoryRepository;
import ru.test.demo.newsfeedapplication.model.repositories.NewsRepository;

@Service
public class NewsService {

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;

    public NewsService(CategoryRepository categoryRepository,
                       NewsRepository newsRepository) {
        this.categoryRepository = categoryRepository;
        this.newsRepository = newsRepository;
    }

    public ResponseEntity<?> getNewsAll() {
        return null;
    }


    public ResponseEntity<?> getCategoryIdNews() {
        return null;
    }


    public ResponseEntity<?> getNewsSearch() {
        return null;
    }


    public ResponseEntity<?> postCategoryIdAddNews() {
        return null;
    }


    public ResponseEntity<?> putNewsEditId() {
        return null;
    }


    public ResponseEntity<?> deleteNewsId() {
        return null;
    }
}
