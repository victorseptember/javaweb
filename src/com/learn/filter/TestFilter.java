package com.learn.filter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestFilter", urlPatterns = ("/tsfilter"))
public class TestFilter extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        //设置响应编码格式
        //获取请求
        //处理请求
        //将请求返回
        System.out.println("=====1=====");

    }
}
