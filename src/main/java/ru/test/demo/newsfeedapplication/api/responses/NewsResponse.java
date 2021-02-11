package ru.test.demo.newsfeedapplication.api.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.demo.newsfeedapplication.model.entities.News;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsResponse {

    private long id;
    private String name;
    private String text;
    private LocalDateTime date;
    private String category;
    private String message;

    public NewsResponse(News news) {
        this.id = news.getId();
        this.name = news.getName();
        this.text = news.getText();
        this.date = news.getDate();
        this.category = news.getCategory().getName();
    }

    public NewsResponse(String message) {
        this.message = message.concat(" not found!");
    }
}
