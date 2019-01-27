package ru.inno.lec08.HW.chatV2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocketReader extends Thread {
    private final Socket socket;
    private BufferedReader reader;


    public ClientSocketReader(Socket socket) {
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {

        while (true) {

            String message = getMessage();

            if (isInterrupted()) break;

            if(socket.isClosed()) break;

            if(message == null) {
                System.out.println("got null message from server");
                break;
            }
            System.out.println(message);
        }

        close();
        System.out.println("client socket reader closed");
    }



    public String getMessage() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void close(){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


