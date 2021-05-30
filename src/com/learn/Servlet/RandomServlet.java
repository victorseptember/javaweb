package com.learn.Servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class RandomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedImage image = new BufferedImage(80,40,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.white);
        //graphics.fillRect(0,0,80,40);
        graphics.setFont(new Font(null,Font.BOLD,20));
        String ran = randomString(7);

        System.out.println(ran);

        graphics.drawString(ran,0,20);

        resp.setHeader("ContentType","jpeg");
        ImageIO.write(image,"jpg",resp.getOutputStream()); //图片流输出
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
        //super.doPost(req, resp);
    }

    public String randomString(int length){
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i=0;i<length;i++){
            int a = random.nextInt(9);
            String num = String.valueOf(a);
            stringBuffer.append(num);
        }
        return stringBuffer.toString();
    }
}
