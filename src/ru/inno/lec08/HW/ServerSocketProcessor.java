package ru.inno.lec08.HW;

import java.io.*;
import java.net.Socket;

public class ServerSocketProcessor extends Thread{
    private final Server server;
    private  Socket socket;
    private  BufferedReader br;
    private  BufferedWriter bw;
    public String name;

    public ServerSocketProcessor(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("server socket processor for  " + socket + " started");


        try {

            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //reading from client socket
            String message = null;

            //read name
            name = br.readLine();
            System.out.println("client  " + name + " connected");
            sendToClent(name + " welcome on board!");
            server.sendToAll(name + " joined the chat", this);

            while (true) {
                message = br.readLine();

                if (isInterrupted()) break;

                if (message.equals("quit")) {

                    server.sendToAll(name + " leaved the chat", this);
                    sendToClent("quit");
                    server.removeClientThread(this);
                    closeResourses();
                    break;
                }

                //write to all
                server.sendToAll(name + " : " + message, this);
            }


        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("server socket processor for " + socket + "closed");

    }


    public void closeResourses(){
        try {
            br.close();
            bw.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("close resourses error");
        }
    }


    public void sendToClent(String message) {

        try {
            bw.write(message + "\n");
            bw.flush();

        } catch (IOException e) {
            System.out.println("send message error");
        }
    }
}
