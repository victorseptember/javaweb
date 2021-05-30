package com.learn.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket_demo {
    public static void main(String[] args) {
        try{
            ServerSocket server = null;
            Socket socket = null;

            /*创建一个ServerSocket在端口8388监听请求*/
            try{
                server = new ServerSocket(8388);
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*使用accept（）阻塞等待客户的请求，有请求到来则创建socket对象*/
            try{
                socket = server.accept();
            } catch (Exception e) {
                e.printStackTrace();
            }

            String line = null;

            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Client:"+is.readLine());
            line = sin.readLine();

            while(!line.equals("exit")) {
                os.println(line);
                os.flush();
                System.out.println("Server:"+line);
                System.out.println("Client:"+is.readLine());
                line = sin.readLine();
            }

            os.close();
            is.close();
            server.close();
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
