package com.newswebservice.newswebservice.Controller;

import com.newswebservice.newswebservice.Entity.dto.TemplateDTO;
import com.newswebservice.newswebservice.Service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TemplateController {

    @Autowired
    private TemplateService templateService; // Service for working with generate template

    /**
     * Controller method to get the change template page view
     * @param model
     * @return
     */
    @RequestMapping(value = "/changeTemplate", method = RequestMethod.GET)
    public String changeTemplate(Model model){
        return "newTemplatePage.html";
    }

    /**
     * Controller method for update new template
     * @param templateDTO
     */
    @RequestMapping(value = "/saveTemplate", method = RequestMethod.POST)
    public @ResponseBody
    void saveTemplate(TemplateDTO templateDTO){
        templateService.saveTemplate(templateDTO);
    }

}
