package com.xiwai.netlearning.augu23;

import java.io.*;
import java.net.*;

public class test1 {
    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务器已经启动，等待链接中");
            while (true) {
                Socket ClientSocket = serverSocket.accept();
                System.out.println("客户端已经链接" + ClientSocket.getInetAddress());
                new Thread(new ClientHandler(ClientSocket)).start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream())) {
            String inputLine;

                while((inputLine=in.readLine())!=null)
                {
                    System.out.println("收到客户消息"+inputLine);
                    out.println("回馈："+inputLine);
                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}