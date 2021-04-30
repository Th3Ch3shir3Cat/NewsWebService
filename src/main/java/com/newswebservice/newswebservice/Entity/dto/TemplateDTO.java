package com.newswebservice.newswebservice.Entity.dto;

/**
 * This DTO is used as a layer between the database and the data
 */

public class TemplateDTO {

    private String templateNews;

    public String getTemplateNews() {
        return templateNews;
    }

    public void setTemplateNews(String templateNews) {
        this.templateNews = templateNews;
    }
}
