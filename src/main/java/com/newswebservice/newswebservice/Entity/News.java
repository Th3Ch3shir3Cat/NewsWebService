package com.newswebservice.newswebservice.Entity;

import javax.persistence.*;

@Entity
@Table(name = "News")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "title")
    private String title;
    @Column(name = "publicationDate")
    private String publicationDate;
    @Column(name = "bodyNews")
    private String bodyNews;
    @Column(name = "pathToImage")
    private String pathToImage;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "template_id")
    private Template template;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getBodyNews() {
        return bodyNews;
    }

    public void setBodyNews(String bodyNews) {
        this.bodyNews = bodyNews;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
