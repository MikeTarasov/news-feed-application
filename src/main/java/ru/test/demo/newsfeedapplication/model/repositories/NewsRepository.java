package ru.test.demo.newsfeedapplication.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.test.demo.newsfeedapplication.model.entities.Category;
import ru.test.demo.newsfeedapplication.model.entities.News;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findByCategory(Category category);

    @Query("select n from News n where lower(n.name) like lower(:text) or lower(n.text) like lower(:text) " +
            "order by n.date desc")
    List<News> searchInNameAndText(String text);

    @Query("select n from News n where (lower(n.name) like lower(:text) or lower(n.text) like lower(:text)) " +
            "and n.category = :category order by n.date desc")
    List<News> searchInNameAndTextInCategory(String text, Category category);
}
