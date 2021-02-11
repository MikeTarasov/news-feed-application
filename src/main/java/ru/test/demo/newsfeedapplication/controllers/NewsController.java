package ru.test.demo.newsfeedapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test.demo.newsfeedapplication.api.requests.PostPutNewsRequest;
import ru.test.demo.newsfeedapplication.service.NewsService;

@RestController
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news/all")
    public ResponseEntity<?> getNewsAll(@RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "10") int limit,
                                        @RequestParam(defaultValue = "all") String period) {
        return newsService.getNewsAll();
    }

    @GetMapping("/news/category/{id}")
    public ResponseEntity<?> getCategoryIdNews(@PathVariable long id,
                                               @RequestParam(defaultValue = "0") int offset,
                                               @RequestParam(defaultValue = "10") int limit,
                                               @RequestParam(defaultValue = "all") String period) {
        return newsService.getCategoryIdNews();
    }

    @GetMapping("/news/search")
    public ResponseEntity<?> getNewsSearch(@RequestParam String text,
                                           @RequestParam(defaultValue = "0") int offset,
                                           @RequestParam(defaultValue = "10") int limit,
                                           @RequestParam(defaultValue = "all") String period) {
        return newsService.getNewsSearch();
    }

    @PostMapping("/category/{id}/add-news")
    public ResponseEntity<?> postCategoryIdAddNews(@PathVariable("id") long categoryId,
                                                   @RequestBody PostPutNewsRequest request) {
        return newsService.postCategoryIdAddNews();
    }

    @PutMapping("/news/edit/{id}")
    public ResponseEntity<?> putNewsEditId(@PathVariable("id") long newsId,
                                           @RequestBody PostPutNewsRequest request) {
        return newsService.putNewsEditId();
    }

    @DeleteMapping("/news/delete/{id}")
    public ResponseEntity<?> deleteNewsId() {
        return newsService.deleteNewsId();
    }
}
