package ru.inno.lec08.HW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocketReader extends Thread {
    private final Socket socket;

    public ClientSocketReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        //System.out.println("client socket reader started");

        try (InputStream is = socket.getInputStream()) {

            BufferedReader in = new BufferedReader(new InputStreamReader(is));

            while (true) {

                String message = in.readLine();

                if (isInterrupted()) break;

                if(socket.isClosed()) break;

                if(message == null) {
                    System.out.println("got null message from server");
                    break;
                }
                System.out.println(message);
            }

        }catch (IOException e){
            System.out.println(e);
        }

        System.out.println("client socket reader closed");
    }
}
