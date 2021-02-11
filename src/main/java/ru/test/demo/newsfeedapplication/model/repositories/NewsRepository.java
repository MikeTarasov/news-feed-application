package ru.test.demo.newsfeedapplication.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.demo.newsfeedapplication.model.entities.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
