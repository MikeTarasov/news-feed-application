package ru.test.demo.newsfeedapplication.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.demo.newsfeedapplication.model.entities.News;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {

    private long id;
    private String name;
    private String text;
    private LocalDateTime date;

    public NewsResponse(News news) {
        this.id = news.getId();
        this.name = news.getName();
        this.text = news.getText();
        this.date = news.getDate();
    }
}
