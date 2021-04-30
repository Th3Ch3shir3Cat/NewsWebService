package com.newswebservice.newswebservice.Service;

import org.springframework.web.servlet.View;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * This class was created when i try to created html template dynamically
 */
public class MyPlainHtmlView implements View {

    private final String htmlDocument;

    public MyPlainHtmlView(String htmlDocument) {
        this.htmlDocument = htmlDocument;
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ServletOutputStream out = httpServletResponse.getOutputStream();
        out.write(this.htmlDocument.getBytes("utf-8"));
    }
}
