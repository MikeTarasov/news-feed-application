package ru.test.demo.newsfeedapplication.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.test.demo.newsfeedapplication.api.responses.NewsResponse;
import ru.test.demo.newsfeedapplication.model.entities.Category;
import ru.test.demo.newsfeedapplication.model.repositories.CategoryRepository;
import ru.test.demo.newsfeedapplication.model.repositories.NewsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;


    public CategoryService(CategoryRepository categoryRepository, NewsRepository newsRepository) {
        this.categoryRepository = categoryRepository;
        this.newsRepository = newsRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll(Sort.by("name"));
    }

    public String getCategoryId(long id, Model model) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) return null;
        Category category = optionalCategory.get();

        List<Category> allCategories = getAllCategories();
        List<NewsResponse> categoryNews = new ArrayList<>();
        newsRepository.findByCategory(category)
                .forEach(news -> categoryNews.add(new NewsResponse(news)));

        model.addAttribute("current_category", " категории ".concat(category.getName()));
        model.addAttribute("all_categories", allCategories);
        model.addAttribute("all_news", categoryNews);

        return "index";
    }
}
