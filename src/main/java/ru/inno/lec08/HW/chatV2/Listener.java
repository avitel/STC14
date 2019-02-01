package ru.inno.lec08.HW.chatV2;

import java.io.*;
import java.net.Socket;

/**
 * thread for listening port and connect new clients
 *
 */
public class Listener extends Thread {

    private final Server server;


    public Listener(Server server) {
        this.server = server;

    }


    @Override
    public void run() {

        System.out.println("server listener has started");

        while (true) {
            try {
                Socket socket = server.getServerSocket().accept();

                if (isInterrupted()) break;

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String name = "";
                Thread.sleep(100);
                name = br.readLine();

                ClientData c = new ClientData(socket, name, bw, br);

                server.addClient(c);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("server listener closed");
    }
}
