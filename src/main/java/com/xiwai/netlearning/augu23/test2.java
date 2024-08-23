package com.xiwai.netlearning.augu23;
import java.net.*;
import java.io.*;
public class test2 {
    public static void main(String[] args) {
        String serverAddress="localhost";
        int port =12345;
        try(Socket socket=new Socket(serverAddress,port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)))    {
            String UserInput;
            System.out.println("请输入消息到服务器，按command+c退出");
            while((UserInput=stdIn.readLine())!=null)
            {
                out.println(UserInput);
                System.out.println("服务器回复"+in.readLine());
            }

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}


