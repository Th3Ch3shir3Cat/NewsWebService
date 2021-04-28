package com.newswebservice.newswebservice.Controller;

import com.newswebservice.newswebservice.Entity.News;
import com.newswebservice.newswebservice.Entity.dto.NewsDTO;
import com.newswebservice.newswebservice.Service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class MainPageController {

    private static String UPLOAD_DIR = "C:\\resources" + "\\images";
    private static int[] ARRAY_NUMBER_NEWS = new int[]{10, 20, 50};

    @Autowired
    private MainPageService mainPageService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(Model model, @PageableDefault Pageable pageable){
        Page<News> arrayNews = mainPageService.getAllNews(pageable);
        model.addAttribute("arrayNews", arrayNews);
        model.addAttribute("arrayNumberNews",ARRAY_NUMBER_NEWS);
        model.addAttribute("url", "/");
        return "news.html";
    }

    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public String addNews(NewsDTO newsDTO, Model model, Pageable pageable){
        String result = null;
        try{
            result = this.saveUploadedFiles(newsDTO.getImages());
        }catch (IOException e){
            e.printStackTrace();

        }
        if(result != null){
            newsDTO.setPathToImage(result);
        }
        News news = mainPageService.addNews(newsDTO);
        Page<News> testList = mainPageService.getAllNews(pageable);
        model.addAttribute("arrayNews",mainPageService.getAllNews(pageable));
        model.addAttribute("arrayNumberNews",ARRAY_NUMBER_NEWS);
        model.addAttribute("url", "/");
        return "listNews.html";
    }


    private String saveUploadedFiles(MultipartFile[] files) throws IOException {

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


}
