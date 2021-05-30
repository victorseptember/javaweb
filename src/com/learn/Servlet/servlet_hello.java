package com.learn.Servlet;

import javax.servlet.*;
import java.io.IOException;

public class servlet_hello implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Servlet 创建了");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getOutputStream().write("<h1>Hello Servlet</h1>".getBytes());
        System.out.println("Servlet响应请求");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Servlet 销毁了");
    }
}
