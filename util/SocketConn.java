package com.company.util;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SocketConn extends Thread{
    private ServerSocket serverSocket;

    public SocketConn()throws IOException{
        try {
            serverSocket = new ServerSocket(Values.SOCKET_PORT);
        }catch (BindException e){
            serverSocket = new ServerSocket(Values.SOCKET_PORT_SECONDARY);
        }
        serverSocket.setSoTimeout(0);
    }

    public void run(){
        try{
            System.out.println("Listening for connections");
            Socket socket = serverSocket.accept();
            System.out.println("Connected to "+socket.getRemoteSocketAddress());

            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println(in.readUTF());

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Connected to "+socket.getLocalAddress());

            socket.close();

        }catch (SocketTimeoutException e){
            System.out.println("connection timed out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bind(String server,int port){
        try{
            Socket socket = new Socket(server,port);
            System.out.println("Connected to "+server+" on port "+port);

            OutputStream outStream = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outStream);

            out.writeUTF("Hello from "+socket.getLocalSocketAddress());

            InputStream inputStream = socket.getInputStream();
            DataInputStream in = new DataInputStream(inputStream);

            System.out.println("Remote says "+ in.readUTF());

            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
