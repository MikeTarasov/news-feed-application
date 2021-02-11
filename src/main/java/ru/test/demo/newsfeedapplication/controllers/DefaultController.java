package ru.test.demo.newsfeedapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.test.demo.newsfeedapplication.model.entities.Category;
import ru.test.demo.newsfeedapplication.model.entities.News;
import ru.test.demo.newsfeedapplication.service.CategoryService;
import ru.test.demo.newsfeedapplication.service.NewsService;

import java.util.List;

@Controller
public class DefaultController {

    private final CategoryService categoryService;
    private final NewsService newsService;

    public DefaultController(CategoryService categoryService, NewsService newsService) {
        this.categoryService = categoryService;
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<Category> allCategories = categoryService.getAllCategories();
        List<News> allNews = newsService.getAllNews();

        model.addAttribute("all_categories", allCategories);
        model.addAttribute("all_news", allNews);

        return "index";
    }
}
