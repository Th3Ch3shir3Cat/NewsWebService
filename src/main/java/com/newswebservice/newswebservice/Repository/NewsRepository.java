package com.newswebservice.newswebservice.Repository;

import com.newswebservice.newswebservice.Entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News, Integer> {
    Page<News> findAll(Pageable pageable);
}
