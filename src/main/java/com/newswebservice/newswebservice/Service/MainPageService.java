package com.newswebservice.newswebservice.Service;

import com.newswebservice.newswebservice.Entity.News;
import com.newswebservice.newswebservice.Entity.dto.NewsDTO;
import com.newswebservice.newswebservice.Repository.NewsRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MainPageService {

    // Linux: /home/{user}/test
    // Windows: C:/Users/{user}/images
    private static String UPLOAD_DIR = System.getProperty("user.home") + "/images";

    @Autowired
    private NewsRepository newsRepository;

    public Page<News> getAllNews(Pageable pageable){
        return newsRepository.findAll(pageable);
    }

    /**
     * Save to database
     * @param newsDTO
     * @return
     */
    public News addNews(NewsDTO newsDTO){
        News news = new News();
        news.setTitle(newsDTO.getTitle());
        news.setBodyNews(newsDTO.getBodyNews());
        news.setPathToImage(newsDTO.getPathToImage());
        news.setPublicationDate(LocalDate.now().toString(DateTimeFormat.forPattern("dd/MM/yyyy")));
        return newsRepository.save(news);
    }

    /**
     * Save image file
     * @param files
     * @return
     * @throws IOException
     */
    public String saveUploadedFiles(MultipartFile[] files) throws IOException {

        // Make sure directory exists!
        File uploadDir = new File(UPLOAD_DIR);
        uploadDir.mkdirs();
        String uploadFilePath = null;
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
            uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);
        }
        return uploadFilePath;
    }

    /**
     * Create or update file template
     * @param bodyTemplate
     * @throws IOException
     */
    public void writeTemplateInFile(String bodyTemplate) throws IOException {
        File file = new File("src/main/resources/templates/fragments/templateBody.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(bodyTemplate);
        bw.close();
    }

}
