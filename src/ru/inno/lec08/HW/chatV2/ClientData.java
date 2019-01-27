package ru.inno.lec08.HW.chatV2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;


/**
 * Class for store client connection parameters
 */
public class ClientData {

    private Socket socket;
    private String name;
    private BufferedWriter writer;
    private BufferedReader reader;


    public ClientData(Socket socket, String name, BufferedWriter bw, BufferedReader br) {
        this.socket = socket;
        this.name = name;
        this.writer = bw;
        this.reader = br;
    }

    public String getName() {
        return name;
    }

    public BufferedReader getReader() {
        return reader;
    }


    /**
     * method sends message to current client
     * @param message
     */
    public boolean sendToClent(String message) {

        if (socket.isClosed()){
            System.out.println("send message error. socket closed");
            return false;
        }

        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            return false;
        }

        return true;
    }


    /**
     * Close resourses
     */
    public void closeResourses(){

        if (socket.isClosed()){
            return;
        }

        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("close resourses error");
        }
    }

}
