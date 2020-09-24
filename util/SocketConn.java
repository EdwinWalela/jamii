package com.company.util;

import org.json.simple.JSONObject;

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

        }catch (SocketTimeoutException e){
            System.out.println("connection timed out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataOutputStream bind(String server,int port){
        DataOutputStream out = null;
        try{
            Socket socket = new Socket(server,port);
            System.out.println("Connected to "+server+" on port "+port);

            OutputStream outStream = socket.getOutputStream();
            out = new DataOutputStream(outStream);


        }catch (IOException e){
            e.printStackTrace();
        }
        // Return output stream
        return out;
    }

}
