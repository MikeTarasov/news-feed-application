package ru.test.demo.newsfeedapplication.service;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.test.demo.newsfeedapplication.api.requests.PostPutNewsRequest;
import ru.test.demo.newsfeedapplication.api.responses.NewsResponse;
import ru.test.demo.newsfeedapplication.model.entities.Category;
import ru.test.demo.newsfeedapplication.model.entities.News;
import ru.test.demo.newsfeedapplication.model.repositories.CategoryRepository;
import ru.test.demo.newsfeedapplication.model.repositories.NewsRepository;

import java.util.*;

@Service
public class NewsService {

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;
    private final CategoryService categoryService;

    public NewsService(CategoryRepository categoryRepository,
                       NewsRepository newsRepository,
                       CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.newsRepository = newsRepository;
        this.categoryService = categoryService;
    }


    private NewsResponse convertToResponse(News news) {
        return new NewsResponse(news);
    }


    private PostPutNewsRequest convertMap(Map<String, String> request) {
        return new PostPutNewsRequest(request.get("name"), request.get("text"), request.get("category"));
    }


    private ResponseEntity<NewsResponse> notFound(String message, int code) {
        return ResponseEntity.status(code).body(new NewsResponse(message));
    }


    private Category createNewCategory(String name) {
        Category category = new Category(name);
        categoryRepository.saveAndFlush(category);
        return category;
    }


    private void deleteEmptyCategory(Category category) {
        if (newsRepository.findByCategory(category).size() == 0) {
            categoryRepository.delete(category);
        }
    }


    public List<News> getAllNews() {
        return newsRepository.findAll(Sort.by("date").descending());
    }


    public ResponseEntity<?> getNewsById(long id) {
        return newsRepository.findById(id)
                .map(news -> ResponseEntity.status(200).body(convertToResponse(news)))
                .orElseGet(() -> notFound("News", 404));
    }


    public ResponseEntity<?> postNewsAdd(Map<String, String> requestBody) {
        return postPutNews(0L, requestBody, "post");
    }


    public ResponseEntity<?> putNewsEditId(long newsId, Map<String, String> requestBody) {
        return postPutNews(newsId, requestBody, "put");
    }


    private ResponseEntity<?> postPutNews(long newsId, Map<String, String> requestBody, String type) {
        PostPutNewsRequest request = convertMap(requestBody);

        String title = request.getTitle();
        String text = request.getText();
        String categoryName = request.getCategory();

        if (title == null || text == null || categoryName == null ||
                title.equals("") || text.equals("") || categoryName.equals("")) {
            return notFound("All the fields are required!", 400);
        }

        Category category = categoryRepository.findCategoryByName(categoryName);
        if (category == null) category = createNewCategory(categoryName);

        News news = new News();
        Category oldCategory = null;
        switch (type) {
            case "put":
                Optional<News> optionalNews = newsRepository.findById(newsId);
                if (optionalNews.isEmpty()) {
                    return notFound("News", 404);
                }
                news = optionalNews.get();

                news.setName(title);
                news.setText(text);
                news.setDate(new Date(System.currentTimeMillis()));
                oldCategory = news.getCategory();
                news.setCategory(category);
                break;
            case "post":
                news = new News(title, text, category);
                break;
        }

        newsRepository.saveAndFlush(news);
        if (oldCategory != null) {
            deleteEmptyCategory(oldCategory);
        }

        return ResponseEntity.status(200).body(news.getId());
    }


    public ResponseEntity<?> deleteNewsId(long id) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isEmpty()) {
            return notFound("News", 404);
        }

        News news = optionalNews.get();
        newsRepository.delete(news);
        deleteEmptyCategory(news.getCategory());

        return ResponseEntity.status(200).body("ok");
    }


    public String getNewsSearch(String text, String categoryName, Model model) {
        List<NewsResponse> findNews = new ArrayList<>();
        List<Category> allCategories = categoryService.getAllCategories();

        if (categoryName.equals(" ")) {
            newsRepository.searchInNameAndText("%".concat(text).concat("%"))
                    .forEach(news -> findNews.add(new NewsResponse(news)));
        } else {
            newsRepository.searchInNameAndTextInCategory(
                    "%".concat(text).concat("%"), categoryRepository.findCategoryByName(categoryName))
                    .forEach(news -> findNews.add(new NewsResponse(news)));
            categoryName = " в категории ".concat(categoryName);
        }

        model.addAttribute("current_category", categoryName);
        model.addAttribute("all_categories", allCategories);
        model.addAttribute("all_news", findNews);

        return "index";
    }
}