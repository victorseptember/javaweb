package com.learn.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket_demo {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1",8388);

            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = null;
            line = sin.readLine();

            while(!line.equals("exit")){
                os.println(line);
                os.flush();
                System.out.println("Client:"+line);
                System.out.println("Server:"+is.readLine());
                line = sin.readLine();
            }

            os.close();
            is.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
