package ru.test.demo.newsfeedapplication.api.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.demo.newsfeedapplication.model.entities.News;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsResponse {

    private long id;
    private String name;
    private String text;
    private String date;
    private String category;
    private String message;

    public NewsResponse(News news) {
        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.id = news.getId();
        this.name = news.getName();
        this.text = news.getText();
        this.date = simpleDateFormat.format(news.getDate());
        this.category = news.getCategory().getName();
    }

    public NewsResponse(String message) {
        this.message = message.concat(" not found!");
    }
}
