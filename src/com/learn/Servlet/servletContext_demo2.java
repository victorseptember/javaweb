package com.learn.Servlet;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class servletContext_demo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        Integer count = (Integer) sc.getAttribute("count");

        if (sc.getAttribute("count") == null) {
            sc.setAttribute("count",1);
        }else {
            sc.setAttribute("count",++count);
        }

        String result = "该站点被访问了"+count+"次";
        resp.getOutputStream().write(result.getBytes());
    }
}
