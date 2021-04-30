package com.newswebservice.newswebservice.Controller;

import com.newswebservice.newswebservice.Entity.News;
import com.newswebservice.newswebservice.Entity.dto.NewsDTO;
import com.newswebservice.newswebservice.Service.MainPageService;
import com.newswebservice.newswebservice.Service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class MainPageController {

    private static int[] ARRAY_NUMBER_NEWS = new int[]{10, 20, 50}; //List of values for the number of records per page

    @Autowired
    private MainPageService mainPageService; //Service for main page
    @Autowired
    private TemplateService templateService; // Service for working with generate template
    private static final Logger log = LoggerFactory.getLogger(MainPageController.class);
    /**
     * The variable is made static to always know the page and the number of records
     */
    private static Pageable pageable; //Pagination

    /**
     * Controller method to get the main page view
     * @param model
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(Model model, @PageableDefault Pageable pageable){
        //Get list of news with pagination
        Page<News> arrayNews = mainPageService.getAllNews(pageable);
        MainPageController.pageable = pageable;
        model.addAttribute("arrayNews", arrayNews);
        model.addAttribute("arrayNumberNews",ARRAY_NUMBER_NEWS);
        model.addAttribute("templateBlock", templateService.findOne());
        model.addAttribute("url", "/");
        return "newsPage.html";
    }

    /**
     * Controller method for adding new news
     * @param newsDTO - DTO for convert it to object News
     * @param model
     * @return
     */
    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public String addNews(NewsDTO newsDTO, Model model){
        /**
         * Save image
         */
        String result = null;
        try{
            result = mainPageService.saveUploadedFiles(newsDTO.getImages());
        }catch (IOException e){
            log.error("Some problem with save image");
            e.printStackTrace();
        }
        if(result != null){
            newsDTO.setPathToImage(result);
        }
        mainPageService.addNews(newsDTO);
        model.addAttribute("arrayNews",mainPageService.getAllNews(pageable));
        model.addAttribute("arrayNumberNews",ARRAY_NUMBER_NEWS);
        model.addAttribute("url", "/");
        return "/fragments/listNews.html";
    }

    /**
     * Controller method for displaying news by generate template
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getListNews", method = RequestMethod.POST)
    public String getListNews(Model model) throws IOException {
        /**
         * I try to create html model dynamically,
         * but did not find ways to implement it,
         * so I created a template file (templateBody) for templates from the database
         */
        /*ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("arrayNews", arrayNews);
        MyPlainHtmlView view = new MyPlainHtmlView(templateService.findOne().getBlock());
        modelAndView.setView(view);*/

        mainPageService.writeTemplateInFile(templateService.findOne().getBlock());
        Page<News> arrayNews = mainPageService.getAllNews(pageable);
        model.addAttribute("arrayNews", arrayNews);
        model.addAttribute("arrayNumberNews",ARRAY_NUMBER_NEWS);
        model.addAttribute("url", "/");
        return "/fragments/templateBody.html";
    }

}
