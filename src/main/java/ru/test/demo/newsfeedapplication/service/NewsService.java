package ru.test.demo.newsfeedapplication.service;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.test.demo.newsfeedapplication.model.entities.News;
import ru.test.demo.newsfeedapplication.model.repositories.CategoryRepository;
import ru.test.demo.newsfeedapplication.model.repositories.NewsRepository;

import java.util.List;

@Service
public class NewsService {

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;

    public NewsService(CategoryRepository categoryRepository,
                       NewsRepository newsRepository) {
        this.categoryRepository = categoryRepository;
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll(Sort.by("date").descending());
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
