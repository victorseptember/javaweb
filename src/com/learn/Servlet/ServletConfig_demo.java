package com.learn.Servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class ServletConfig_demo extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        Enumeration enums = config.getInitParameterNames();
        while(enums.hasMoreElements()) {
            String name = (String) enums.nextElement();
            String value = config.getInitParameter(name);
            System.out.println(value);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getOutputStream().write("<h2>访问servlet，调用service()方法</h 2>".getBytes());
    }
}
