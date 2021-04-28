package com.newswebservice.newswebservice.Service;

import com.newswebservice.newswebservice.Entity.News;
import com.newswebservice.newswebservice.Entity.dto.NewsDTO;
import com.newswebservice.newswebservice.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MainPageService {
    @Autowired
    private NewsRepository newsRepository;

    public Page<News> getAllNews(Pageable pageable){
        return newsRepository.findAll(pageable);
    }

    public News addNews(NewsDTO newsDTO){
        News news = new News();
        news.setTitle(newsDTO.getTitle());
        news.setBodyNews(newsDTO.getBodyNews());
        news.setPathToImage(newsDTO.getPathToImage());
        return newsRepository.save(news);
    }
}
