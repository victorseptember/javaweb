package com.learn.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = this.getServletContext().getRealPath("图片.jpg");
        //System.out.println(path);
        //resp.getOutputStream().write(path.getBytes());
        FileInputStream fileInputStream = new FileInputStream(path);
        String fileName = path.substring(path.lastIndexOf("\\")+1);
        //String fileName = "2.jpg";
        //System.out.println(fileName);
        //resp.getOutputStream().write(fileName.getBytes());
        //resp.setHeader("Content-Disposition","attachment;fileName="+ fileName);

        int tempByte = 0;
        byte[] bytes = new byte[1024];
        while((tempByte=fileInputStream.read(bytes))>0) {
            resp.getOutputStream().write(bytes);
        }
        fileInputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
        //super.doPost(req, resp);
    }
}
