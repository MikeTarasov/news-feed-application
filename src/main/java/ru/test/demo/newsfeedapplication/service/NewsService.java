package ru.test.demo.newsfeedapplication.service;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.test.demo.newsfeedapplication.api.requests.PostPutNewsRequest;
import ru.test.demo.newsfeedapplication.api.responses.NewsResponse;
import ru.test.demo.newsfeedapplication.model.entities.Category;
import ru.test.demo.newsfeedapplication.model.entities.News;
import ru.test.demo.newsfeedapplication.model.repositories.CategoryRepository;
import ru.test.demo.newsfeedapplication.model.repositories.NewsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NewsService {

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;

    public NewsService(CategoryRepository categoryRepository,
                       NewsRepository newsRepository) {
        this.categoryRepository = categoryRepository;
        this.newsRepository = newsRepository;
    }

    private NewsResponse convertToResponse(News news) {
        return new NewsResponse(news);
    }

    private PostPutNewsRequest convertMap(Map<String, String> request) {
        return new PostPutNewsRequest(request.get("name"), request.get("text"), request.get("category"));
    }

    private ResponseEntity<NewsResponse> notFound() {
        return ResponseEntity.status(404).body(null);
    }

    private Category createNewCategory(String name) {
        Category category = new Category(name);
        categoryRepository.saveAndFlush(category);
        return category;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll(Sort.by("date").descending());
    }

    public ResponseEntity<?> getNewsById(long id) {
        return newsRepository.findById(id)
                .map(news -> ResponseEntity.status(200).body(convertToResponse(news)))
                .orElseGet(this::notFound);
    }

    public ResponseEntity<?> postNewsAdd(Map<String, String> requestBody) {
        PostPutNewsRequest request = convertMap(requestBody);

        String categoryName = request.getCategory();
        if (categoryName == null || categoryName.equals("")) return notFound();

        Category category = categoryRepository.findCategoryByName(categoryName);
        if (category == null) category = createNewCategory(categoryName);

        News news = new News(request.getTitle(), request.getText(), category);
        newsRepository.saveAndFlush(news);

        return ResponseEntity.status(200).body("ok");
    }

    public ResponseEntity<?> deleteNewsId(long id) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isEmpty()) return notFound();

        newsRepository.delete(optionalNews.get());

        return ResponseEntity.status(200).body("ok");
    }

    public ResponseEntity<?> putNewsEditId(long newsId, Map<String, String> requestBody) {
        PostPutNewsRequest request = convertMap(requestBody);

        String categoryName = request.getCategory();
        if (categoryName == null || categoryName.equals("")) return notFound();

        Category category = categoryRepository.findCategoryByName(categoryName);
        if (category == null) category = createNewCategory(categoryName);

        Optional<News> optionalNews = newsRepository.findById(newsId);
        if (optionalNews.isEmpty()) return notFound();

        News news = optionalNews.get();
        news.setName(request.getTitle());
        news.setText(request.getText());
        news.setDate(LocalDateTime.now());
        news.setCategory(category);

        return ResponseEntity.status(200).body(news.getId());
    }


    public ResponseEntity<?> getNewsSearch() {
        return null;
    }









}
