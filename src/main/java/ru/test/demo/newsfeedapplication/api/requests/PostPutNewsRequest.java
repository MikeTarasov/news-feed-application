package ru.test.demo.newsfeedapplication.api.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPutNewsRequest {

    private String title;
    private String text;
    private String category;
}
