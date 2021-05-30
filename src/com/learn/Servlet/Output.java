package com.learn.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Output extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // ServletOutputStream output = resp.getOutputStream();
        //output.println("aaaa");
        //resp.setHeader("Content-Type","text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //resp.setCharacterEncoding("UTF-8");
        //output.write("成功".getBytes("UTF-8"));
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("成功！");
        //resp.setContentType("text/html;charset=UTF-8");
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
        //super.doPost(req, resp);
    }
}
