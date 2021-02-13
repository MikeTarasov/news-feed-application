package ru.test.demo.newsfeedapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.test.demo.newsfeedapplication.service.NewsService;

import java.util.Map;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable long id) {
        return newsService.getNewsById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> postNewsAdd(@RequestParam Map<String, String> request) {
        return newsService.postNewsAdd(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putNewsEditId(@PathVariable("id") long newsId,
                                           @RequestParam Map<String, String> request) {
        return newsService.putNewsEditId(newsId, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNewsId(@PathVariable long id) {
        return newsService.deleteNewsId(id);
    }

    @GetMapping("/search")
    public String getNewsSearch(@RequestParam String text,
                                @RequestParam String category,
                                Model model) {
        return newsService.getNewsSearch(text, category, model);
    }
}