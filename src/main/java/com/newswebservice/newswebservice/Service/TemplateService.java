package com.newswebservice.newswebservice.Service;

import com.newswebservice.newswebservice.Entity.Template;
import com.newswebservice.newswebservice.Entity.dto.TemplateDTO;
import com.newswebservice.newswebservice.Repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    /**
     * Find the first entry in the database
     * @return
     */
    public Template findOne(){
        List<Template> templates = templateRepository.findAll();
        if(templates.size() == 0){
            return null;
        }
        return templates.get(0);
    }

    /**
     * Save template body in database
     * @param templateDTO
     */
    public void saveTemplate(TemplateDTO templateDTO){
        Template template = this.findOne();
        if(template == null){
            template = new Template();
        }
        template.setBlock("<div id = \"arrayNews\">\n" +
                "  <div th:replace=\"pagination.html\"></div>\n" +
                "  <li th:each=\"news : ${arrayNews}\" class=\"list-group-item\">" + templateDTO.getTemplateNews()
        + "  </li>\n" +
                "</div>");
        templateRepository.save(template);
    }

}
