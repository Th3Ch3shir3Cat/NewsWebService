package com.newswebservice.newswebservice.Service;

import org.antlr.stringtemplate.StringTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Map;

public class GenericTemplateEngine {
    private final TemplateEngine templateEngine;

    public GenericTemplateEngine(TemplateMode templateMode) {

        templateEngine = new org.thymeleaf.TemplateEngine();
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(templateMode);
        templateResolver.setCacheable(false);
        this.templateEngine.setTemplateResolver(templateResolver);
    }

    public String getTemplate(String templateName, Map<String, Object> parameters) {
        Context ctx = new Context();
        if (parameters != null) {
            parameters.forEach(ctx::setVariable);
        }
        return this.templateEngine.process(templateName, ctx);
    }
}
